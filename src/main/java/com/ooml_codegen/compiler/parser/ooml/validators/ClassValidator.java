package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.parser.ContextStack;
import com.ooml_codegen.compiler.parser.ContextType;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.inheritance.ClassInheritance;
import com.ooml_codegen.models.inheritance.InterfaceInheritance;
import com.ooml_codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.List;

public class ClassValidator extends Validator {

	private final Class clazz = new Class();

	private final ContextStack contextStack = new ContextStack();

	public ClassValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	public Class getValidatedClass() {
		return this.clazz;
	}

	@Override
	public void validate() throws Exception {
		this.validateAccessModifier();
		this.validateBehaviorModifiers();
		this.validateClassDeclaration();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();
		this.validateClassBody();

		System.out.println(this.clazz.getGenerationOrder());
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
			this.contextStack.push(ContextType.CLASS);
			nextToken = this.nextToken();
		} else if (nextToken.getType() != TokenType.COLON) {
			// TODO
			ULogger.error("Missing : or {");
			throw new Exception();
		}

		List<Token> tokenList = new ArrayList<>();

		while (nextToken.getType() != TokenType.EOF) {
			//System.out.println(this.currentToken);

			switch (nextToken.getType()) {
				case SIGN, WORD, QUOTED_WORD -> {
					tokenList.add(nextToken);
				}
				case COLON -> {
					this.insertTokens(tokenList);
					tokenList.clear();

					this.insertToken(nextToken);

					AttributeValidator attributeValidator = this.newAttributValidator();
					attributeValidator.validate();

					this.clazz.addAttribute(attributeValidator.getValidatedAttribute());
				}
				case ACCESS_MODIFIER_BLOCK -> {
					// TODO manage multiple attributes
				}
				case OPENING_PARENTHESIS -> {
					// TODO manage constructor or method
				}
				case CLOSING_CURLY_BRACKET -> {
					if (this.contextStack.empty()) {
						// TODO
						ULogger.error("unexpected token }");
						throw new Exception();
					}

					this.contextStack.pop();

					if (this.contextStack.empty()) {
						return;
					}
				}
				case PACKAGE -> {
					if (this.contextStack.empty()) {
						this.unConsumedTokenList.add(nextToken);
						return;
					}

					ULogger.error("unexpected token " + nextToken.getValue());
					throw new Exception();
				}
				case CLASS, ENUM, INTERFACE -> {
					if (this.contextStack.empty()) {
						this.unConsumedTokenList.add(nextToken);
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

	@Override
	protected void addPackage(Package cPackage) {
		this.clazz.setPackage(cPackage);
	}

}
