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
import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.inheritance.ClassInheritance;
import com.ooml_codegen.models.inheritance.InterfaceInheritance;
import com.ooml_codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClassValidator extends Validator {

	private Token currentToken;

	private final Class clazz = new Class();

	private final ContextStack contextStack = new ContextStack();

	public ClassValidator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		super(lexerManager, unConsumedTokenList);
	}

	public Class getToBeGeneratedClass() {
		return this.clazz;
	}

	@Override
	public void validate() throws Exception {
		this.currentToken = this.nextToken();

		this.validateAccessModifier();
		this.validateBehaviorModifiers();
		this.validateClassDeclaration();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();
		this.validateClassBody();

		System.out.println(this.clazz.getGenerationOrder());
	}

	protected void addComment(Comment comment) {
		this.clazz.addComment(comment);
	}

	protected void addPackage(Package cPackage) {
		this.clazz.setPackage(cPackage);
	}

	private void validateAccessModifier() throws Exception {
		if (this.currentToken.getType() != TokenType.SIGN) {
			return;
		}

		ClassAccessModifier accessModifier = ClassAccessModifier.getModifierFromOOMLSign(this.currentToken.getValue());
		if (accessModifier == null) {
			// TODO error
			ULogger.error("invalid accessModifier");
			throw new Exception();
		}

		this.clazz.setAccessModifier(accessModifier);
		this.currentToken = this.nextToken();
	}

	private void validateBehaviorModifiers() throws Exception {
		while (this.currentToken.getType() == TokenType.WORD || this.currentToken.getType() == TokenType.QUOTED_WORD) {
			this.clazz.addBehaviorModifier(new BehaviorModifier(this.currentToken.getValue()));

			this.currentToken = this.nextToken();
			if (this.currentToken.getType() == TokenType.EOF || this.currentToken.getType() == TokenType.CLASS) {
				break;
			}
		}
	}

	private void validateClassDeclaration() throws Exception {
		if (this.currentToken.getType() != TokenType.CLASS) {
			ULogger.error("Missing currentToken class");
			throw new Exception();
		}

		this.clazz.addKeyword();

		this.currentToken = this.nextToken();
		if (this.currentToken.getType() != TokenType.WORD && this.currentToken.getType() != TokenType.QUOTED_WORD) {
			// TODO
			ULogger.error("Missing classname");
			throw new Exception();
		}

		this.clazz.setName(new Name(this.currentToken.getValue()));
	}

	private void validateClassInheritance() throws Exception {
		this.currentToken = this.nextToken();
		if (this.currentToken.getType() == TokenType.CLASS_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (this.currentToken.getType() != TokenType.EOF) {
				this.currentToken = this.nextToken();

				if (this.currentToken.getType() == TokenType.INTERFACE_INHERITANCE || this.currentToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
					break;
				}

				tokenList.add(this.currentToken);
			}

			this.validateInheritances(tokenList, TokenType.CLASS_INHERITANCE);
		}
	}

	private void validateInterfaceInheritance() throws Exception {
		if (this.currentToken.getType() == TokenType.INTERFACE_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (this.currentToken.getType() != TokenType.EOF) {
				this.currentToken = this.nextToken();

				if (this.currentToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
					break;
				}

				tokenList.add(this.currentToken);
			}

			this.validateInheritances(tokenList, TokenType.INTERFACE_INHERITANCE);
		}
	}

	private void validateInheritances(List<Token> tokenList, TokenType inheritanceType) throws Exception {
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);

			if (i % 2 == 0) {
				if (token.getType() != TokenType.WORD && token.getType() != TokenType.QUOTED_WORD) {
					// TODO error
					ULogger.error("unexpected " + token.getValue());
					throw new Exception();
				}

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
			if (lastToken.getType() != TokenType.WORD && lastToken.getType() != TokenType.QUOTED_WORD) {
				// TODO error
				ULogger.error("Unexpected currentToken " + lastToken.getValue());
				throw new Exception();
			}
		}
	}

	private void validateClassBody() throws Exception {
		this.handleClassContext();

		List<Token> tokenList = new ArrayList<>();

		while (this.currentToken.getType() != TokenType.EOF) {
			//System.out.println(this.currentToken);

			switch (this.currentToken.getType()) {
				case SIGN, WORD, QUOTED_WORD -> {
					tokenList.add(this.currentToken);
				}
				case COLON -> {
					this.validateAttribut(tokenList);
					tokenList.clear();
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
						this.unConsumedTokenList.add(this.currentToken);
						return;
					}

					ULogger.error("unexpected token " + this.currentToken.getValue());
					throw new Exception();
				}
				case CLASS, ENUM, INTERFACE  -> {
					if (this.contextStack.empty()) {
						this.unConsumedTokenList.add(this.currentToken);
						return;
					}

					// TODO Manage intern class, enum, interface
				}
				default -> {
					// TODO
					ULogger.error("unexpected token " + this.currentToken.getValue() + " at " + this.currentToken.getLocation());
					throw new Exception();
				}
			}

			this.currentToken = this.nextToken();
		}
	}

	private void handleClassContext() throws FileNotFoundException {
		if (this.currentToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
			this.contextStack.push(ContextType.CLASS);
			this.currentToken = this.nextToken();
		} else if (this.currentToken.getType() == TokenType.COLON) {
			this.currentToken = this.nextToken();
		}
	}

	private void validateAttribut(List<Token> beforeColonTokenList) throws Exception {
		if (beforeColonTokenList.size() == 0) {
			ULogger.error("Invalid syntax");
			throw new Exception();
		}

		Token nextToken = beforeColonTokenList.get(0);

		AttributeAccessModifier accessModifier = null;
		if (nextToken.getType() == TokenType.SIGN) {
			beforeColonTokenList.remove(0);

			accessModifier = AttributeAccessModifier.getModifierFromOOMLSign(nextToken.getValue());
			if (accessModifier == null) {
				// TODO
				ULogger.error("Unexpected token");
				throw new Exception();
			}
		}

		if (beforeColonTokenList.size() == 0) {
			// TODO
			ULogger.error("Name not found");
			throw new Exception();
		}

		String attributeName = null;
		List<BehaviorModifier> behaviorModifiers = new ArrayList<>();
		for (int i = 0; i < beforeColonTokenList.size(); i++) {
			Token token = beforeColonTokenList.get(i);

			if (token.getType() != TokenType.WORD || token.getType() != TokenType.QUOTED_WORD) {
				// TODO
				ULogger.error("Unexpected token");
				throw new Exception();
			}

			if (i == beforeColonTokenList.size() - 1) {
				attributeName = token.getValue();
			} else {
				behaviorModifiers.add(new BehaviorModifier(token.getValue()));
			}
		}

		nextToken = this.nextToken();
		if (nextToken.getType() != TokenType.WORD || nextToken.getType() != TokenType.QUOTED_WORD) {
			// TODO
			ULogger.error("Missing type");
			throw new Exception();
		}

		Type type = new Type(nextToken.getValue());

		nextToken = this.nextToken();
		if (nextToken.getType() != TokenType.SEMI_COLON) {
			this.insertTokenInQueue(nextToken);
		}

		Attribute attribute = new Attribute(attributeName, type);
		attribute.setAccessModifier(accessModifier);
		attribute.addBehaviorModifiers(behaviorModifiers);

		this.clazz.addAttribute(attribute);
	}

}
