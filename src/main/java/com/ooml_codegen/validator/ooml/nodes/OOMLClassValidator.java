package com.ooml_codegen.validator.ooml.nodes;

import com.ooml_codegen.lexer.LexerManager;
import com.ooml_codegen.lexer.Token;
import com.ooml_codegen.lexer.TokenType;
import com.ooml_codegen.models.modifiers.BehaviorModifier;
import com.ooml_codegen.utils.UContextStack;
import com.ooml_codegen.utils.enums.ContextType;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.inheritance.ClassInheritance;
import com.ooml_codegen.models.inheritance.InterfaceInheritance;
import com.ooml_codegen.utils.ULogger;
import com.ooml_codegen.validator.ooml.OOMLValidator;

import java.util.ArrayList;
import java.util.List;

public class OOMLClassValidator extends OOMLValidator {

	private final Class clazz = new Class();

	private final UContextStack UContextStack = new UContextStack();

	public OOMLClassValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	public Class getValidatedClass() {
		return this.clazz;
	}

	@Override
	public void validate() throws Exception {
		this.validatePackage();
		this.validateAccessModifier();
		this.validateBehaviorModifiers();
		this.validateClassDeclaration();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();
		this.validateClassBody();

		System.out.println(this.clazz.getGenerationOrder());
	}

	private void validatePackage() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.PACKAGE) {
			// We are sure to have the package name in the following token.
			nextToken = this.nextToken();
			this.clazz.setPackage(new Package(nextToken.getValue()));
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

		ClassAccessModifier accessModifier = ClassAccessModifier.getModifierFromOOMLSign(nextToken.getValue());
		if (accessModifier == null) {
			// TODO error
			ULogger.error("invalid accessModifier");
			throw new Exception();
		}

		this.clazz.setAccessModifier(accessModifier);
	}

	private void validateBehaviorModifiers() throws Exception {
		Token nextToken = this.nextToken();

		while (nextToken.getType() != TokenType.CLASS) {
			if (nextToken.getType() == TokenType.EOF) {
				return;
			}

			this.clazz.addBehaviorModifier(new BehaviorModifier(nextToken.getValue()));

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

		this.clazz.addKeyword();

		nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.EOF) {
			// TODO
			ULogger.error("Missing classname");
			throw new Exception();
		}

		this.clazz.setName(new Name(nextToken.getValue()));
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
					this.clazz.addClassInheritance(new ClassInheritance(token.getValue()));
				} else if (inheritanceType == TokenType.INTERFACE_INHERITANCE) {
					this.clazz.addInterfaceInheritance(new InterfaceInheritance(token.getValue()));
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

					this.insertTokens(unConsumedTokenList);
					unConsumedTokenList = new ArrayList<>();

					this.insertToken(nextToken);

					OOMLAttributeValidator OOMLAttributeValidator = this.newAttributValidator();
					OOMLAttributeValidator.validate();

					this.clazz.addAttribute(OOMLAttributeValidator.getValidatedAttribute());
				}
				case ACCESS_MODIFIER_BLOCK -> {
					currentAccessModifierBlock = nextToken;
				}
				case OPENING_PARENTHESIS -> {
					// TODO manage constructor or method
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

			nextToken = this.nextToken();
		}
	}

	@Override
	protected void addComment(Comment comment) {
		this.clazz.addComment(comment);
	}

}
