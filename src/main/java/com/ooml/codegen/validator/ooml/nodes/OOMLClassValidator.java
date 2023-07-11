package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.nodes.NClass;
import com.ooml.codegen.models.nodes.leafs.LInheritanceClass;
import com.ooml.codegen.models.nodes.leafs.LInheritanceInterface;
import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.utils.UContextStack;
import com.ooml.codegen.utils.UContextStack.ContextType;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.ArrayList;
import java.util.List;

public class OOMLClassValidator extends OOMLValidator {

	private final NClass nClass = new NClass();

	private final UContextStack UContextStack = new UContextStack();

	public OOMLClassValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NClass getValidatedNode() {
		this.nClass.check();
		return this.nClass;
	}

	@Override
	public void validate() throws Exception {
		this.validatePackage();
		this.validateAccessModifier();
		this.validateNonAccessModifiers();
		this.validateClassDeclaration();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();
		this.validateClassBody();
	}

	private void validatePackage() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.PACKAGE) {
			// We are sure to have the package name in the following token.
			nextToken = this.nextToken();
			this.nClass.addChild(new LPackage(nextToken.getValue()));
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateAccessModifier() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() != TokenType.SIGN) {
			this.insertToken(nextToken);
			return;
		}

		this.nClass.addChild(new LAccessModifierClass(nextToken.getValue()));
	}

	private void validateNonAccessModifiers() throws Exception {
		Token nextToken = this.nextToken();

		while (nextToken.getType() != TokenType.CLASS) {
			if (nextToken.getType() == TokenType.EOF) {
				return;
			}

			this.nClass.addChild(new LNonAccessModifier(nextToken.getValue()));

			nextToken = this.nextToken();
		}

		this.insertToken(nextToken);
	}

	private void validateClassDeclaration() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() != TokenType.CLASS) {
			ULogger.error("Missing currentToken class");
			throw new Exception();
		}

		this.nClass.addChild(new LDeclaration(nextToken.getValue()));

		nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.EOF) {
			// TODO
			ULogger.error("Missing classname");
			throw new Exception();
		}

		this.nClass.addChild(new LName(nextToken.getValue()));
	}

	private void validateClassInheritance() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.CLASS_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (nextToken.getType() != TokenType.EOF) {
				nextToken = this.nextToken();

				if (nextToken.getType() == TokenType.INTERFACE_INHERITANCE
						|| nextToken.getType() == TokenType.OPENING_CURLY_BRACKET
						|| nextToken.getType() == TokenType.COLON
				) {
					this.insertToken(nextToken);
					break;
				}

				tokenList.add(nextToken);
			}

			this.validateInheritances(TokenType.CLASS_INHERITANCE, tokenList);
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateInterfaceInheritance() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.INTERFACE_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (nextToken.getType() != TokenType.EOF) {
				nextToken = this.nextToken();

				if (nextToken.getType() == TokenType.OPENING_CURLY_BRACKET
						|| nextToken.getType() == TokenType.COLON
				) {
					this.insertToken(nextToken);
					break;
				}

				tokenList.add(nextToken);
			}

			this.validateInheritances(TokenType.INTERFACE_INHERITANCE, tokenList);
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateInheritances(TokenType inheritanceType, List<Token> tokenList) throws Exception {
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);

			if (i % 2 == 0) {
				if (inheritanceType == TokenType.CLASS_INHERITANCE) {
					this.nClass.addChild(new LInheritanceClass(token.getValue()));
				} else if (inheritanceType == TokenType.INTERFACE_INHERITANCE) {
					this.nClass.addChild(new LInheritanceInterface(token.getValue()));
				}
			} else {
				if (token.getType() != TokenType.COMMA) {
					// TODO error
					ULogger.error("missing unexpected token " + token.getValue());
					throw new Exception();
				}
			}
		}

		if (tokenList.size() % 2 == 0) {
			Token lastToken = tokenList.get(tokenList.size() - 1);
			if (lastToken.getType() == TokenType.COLON) {
				// TODO error
				ULogger.error("Unexpected token " + lastToken.getValue());
				throw new Exception();
			}
		}
	}

	private void validateClassBody() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
			this.UContextStack.push(ContextType.CLASS);
			nextToken = this.nextToken();
		} else if (nextToken.getType() == TokenType.COLON) {
			nextToken = this.nextToken();
		} else {
			// TODO
			ULogger.error("Missing : or {");
			throw new Exception();
		}

		List<Token> unConsumedTokenList = new ArrayList<>();
		Token currentAccessModifierBlock = null;

		while (nextToken.getType() != TokenType.EOF) {
			//System.out.println(nextToken);

			switch (nextToken.getType()) {
				case SIGN, WORD, QUOTED_WORD -> {
					if (nextToken.getType() == TokenType.SIGN) {
						currentAccessModifierBlock = null;
					}

					unConsumedTokenList.add(nextToken);
				}
				case COLON -> {
					if (currentAccessModifierBlock != null) {
						this.insertToken(currentAccessModifierBlock);
					}

					unConsumedTokenList.add(nextToken);

					this.insertTokens(unConsumedTokenList);

					System.out.println(unConsumedTokenList);

					unConsumedTokenList = new ArrayList<>();


					OOMLAttributeValidator attributeValidator = this.newAttributValidator();
					attributeValidator.validate();

					this.nClass.addChild(attributeValidator.getValidatedNode());
				}
				case ACCESS_MODIFIER_BLOCK -> {
					currentAccessModifierBlock = nextToken;
				}
				case OPENING_PARENTHESIS -> {
					unConsumedTokenList.add(nextToken);

					while (nextToken.getType() != TokenType.CLOSING_PARENTHESIS) {
						nextToken = this.nextToken(false);

						unConsumedTokenList.add(nextToken);

						if (nextToken.getType() == TokenType.EOF) {
							ULogger.error("unexpected token " + nextToken.getValue());
							throw new Exception();
						}
					}

					nextToken = this.nextToken();

					this.insertTokens(unConsumedTokenList);
					unConsumedTokenList = new ArrayList<>();

					if (nextToken.getType() != TokenType.COLON) {
						OOMLConstructorValidator constructorValidator = this.newConstructorValidator();
						constructorValidator.validate();

						this.nClass.addChild(constructorValidator.getValidatedNode());
					} else {
						this.insertToken(nextToken);

						OOMLMethodValidator methodValidator = this.newMethodValidator();
						methodValidator.validate();

						this.nClass.addChild(methodValidator.getValidatedNode());
					}
				}
				case CLOSING_CURLY_BRACKET -> {
					if (this.UContextStack.empty()) {
						// TODO
						ULogger.error("unexpected token }");
						throw new Exception();
					}

					this.UContextStack.pop();

					if (this.UContextStack.empty()) {
						return;
					}
				}
				case PACKAGE -> {
					if (this.UContextStack.empty()) {
						this.insertTokens(unConsumedTokenList);
						unConsumedTokenList.clear();
						this.insertToken(nextToken);
						return;
					}

					ULogger.error("unexpected token " + nextToken.getValue());
					throw new Exception();
				}
				case CLASS, ENUM, INTERFACE -> {
					if (this.UContextStack.empty()) {
						this.insertTokens(unConsumedTokenList);
						unConsumedTokenList.clear();
						this.insertToken(nextToken);
						return;
					}

					// TODO Manage intern class, enum, interface
				}
				default -> {
					// TODO
					ULogger.error("unexpected token ");
					throw new Exception();
				}
			}

			nextToken = this.nextToken(false);
		}
	}

	@Override
	protected void addComment(LComment comment) {
		this.nClass.addChild(comment);
	}

}
