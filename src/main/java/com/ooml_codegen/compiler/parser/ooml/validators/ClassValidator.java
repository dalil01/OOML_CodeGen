package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.models.BehaviorModifier;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Name;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClassValidator extends Validator {

	private Token currentToken;

	private final Class clazz = new Class();

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
		this.validateClass();
		this.validateClassInheritance();
		this.validateInterfaceInheritance();


		if (this.currentToken.getType() != TokenType.OPENING_CURLY_BRACKET) {
			// TODO
			ULogger.error("Missing {");
			return;
		}

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

	private void validateClass() throws Exception {
		if (this.currentToken.getType() != TokenType.CLASS) {
			ULogger.error("Missing currentToken class");
			return;
		}

		this.currentToken = this.nextToken();
		if (this.currentToken.getType() != TokenType.WORD && this.currentToken.getType() != TokenType.QUOTED_WORD) {
			// TODO
			ULogger.error("Missing classname");
			return;
		}

		this.clazz.addKeyword();
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

			this.validateInheritances(tokenList);
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

			this.validateInheritances(tokenList);
		}
	}

	private void validateInheritances(List<Token> tokenList) throws Exception {
		for (int i = 0; i < tokenList.size(); i++) {
			Token t = tokenList.get(i);

			if (i % 2 == 0) {
				if (t.getType() != TokenType.WORD && t.getType() != TokenType.QUOTED_WORD) {
					// TODO error
					ULogger.error("unexpected " + t.getValue());
					throw new Exception();
				}

				// TODO : manage class inheritance
				// this.clazz.
			} else {
				if (t.getType() != TokenType.COMMA) {
					// TODO error
					ULogger.error("missing comma");
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


}
