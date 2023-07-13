package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.NClass;
import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.utils.UContextStack;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.List;

public class OOMLClassValidator extends OOMLValidator {

	private final NClass nClass = new NClass();

	private final UContextStack UContextStack = new UContextStack();

	public OOMLClassValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NClass getValidatedNode() {
		return this.nClass;
	}

	@Override
	public void validate() throws Exception {

		/*
		this.validateAccessModifier();
		this.validateNonAccessModifiers();
		this.validateClassDeclaration();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();
		this.validateClassBody();

		 */
	}

		/*


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
						nextToken = this.nextToken();

						unConsumedTokenList.add(nextToken);

						if (nextToken.getType() == TokenType.EOF) {
							ULogger.error("unexpected token " + nextToken.getValue());
							throw new Exception();
						}
					}

					nextToken = this.nextToken();

					this.insertTokens(unConsumedTokenList);
					unConsumedTokenList = new ArrayList<>();
					this.insertToken(nextToken);

					if (nextToken.getType() != TokenType.COLON) {
						OOMLConstructorValidator constructorValidator = this.newConstructorValidator();
						constructorValidator.validate();

						this.nClass.addChild(constructorValidator.getValidatedNode());
					} else {
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
					System.out.println(nextToken);
					// TODO
					ULogger.error("unexpected token " + nextToken);
					throw new Exception();
				}
			}

			nextToken = this.nextToken();
		}
	}

	 */

	public void addChild(List<Token> tokenList, Class<? extends Node> clazz) {
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);

			TokenType type = token.getType();
			String value = token.getValue();

			if (token.isComment()) {
				if (type == TokenType.SINGLE_LINE_COMMENT) {
					this.nClass.addChild(new LCommentSingleLine(value));
				} else {
					this.nClass.addChild(new LCommentMultiLine(value));
				}

				continue;
			}

			/*
			if (clazz.equals(LPackage.class)) {
				this.nClass.addChild(new LPackage(tokenList.get(i + 1).getValue()));
				i++;
			} else if (clazz.equals(LAccessModifierClass.class)) {
				//this.nClass.addChild(new LAccessModifierClass());
			}

			 */
		}
	}

	@Override
	public void addChildren(List<Token> tokenList) {
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);

			TokenType type = token.getType();
			String value = token.getValue();

			switch (type) {
				case SINGLE_LINE_COMMENT -> this.nClass.addChild(new LCommentSingleLine(value));
				case MULTI_LINE_COMMENT -> this.nClass.addChild(new LCommentMultiLine(value));
				case PACKAGE -> {
					//this.nClass.addChild(new LPackage(tokenList.get(i + 1).getValue()));
					i++;
				}
				case SIGN -> {
					this.nClass.addChild(new LAccessModifierClass(token.getValue()));
				}
				default -> {
				}
			}
		}
	}

}
