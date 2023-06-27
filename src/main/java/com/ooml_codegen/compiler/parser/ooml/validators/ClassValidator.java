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

import java.util.ArrayList;
import java.util.List;

public class ClassValidator extends Validator {

	private final Class clazz = new Class();

	public ClassValidator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		super(lexerManager, unConsumedTokenList);
	}

	public Class getToBeGeneratedClass() {
		return this.clazz;
	}

	@Override
	public void validate() throws Exception {
		Token currentToken = this.nextToken();

		if (currentToken.getType() == TokenType.SIGN) {
			this.validateAccessModifier(currentToken);
			currentToken = this.nextToken();
		}

		while (currentToken.getType() == TokenType.WORD || currentToken.getType() == TokenType.QUOTED_WORD) {
			this.validateBehaviorModifier(currentToken);

			currentToken = this.nextToken();
			if (currentToken.getType() == TokenType.EOF || currentToken.getType() == TokenType.CLASS) {
				break;
			}
		}

		if (currentToken.getType() == TokenType.CLASS) {
			this.clazz.addKeyword();
		} else {
			ULogger.error("Missing currentToken class");
			return;
		}

		currentToken = this.nextToken();
		if (currentToken.getType() != TokenType.WORD && currentToken.getType() != TokenType.QUOTED_WORD) {
			// TODO
			ULogger.error("Missing classname");
			return;
		}
		this.clazz.setName(new Name(currentToken.getValue()));

		currentToken = this.nextToken();
		if (currentToken.getType() == TokenType.CLASS_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (currentToken.getType() != TokenType.EOF) {
				currentToken = this.nextToken();

				if (currentToken.getType() == TokenType.INTERFACE_INHERITANCE || currentToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
					break;
				}

				tokenList.add(currentToken);
			}

			for (int i = 0; i < tokenList.size(); i++) {
				Token t = tokenList.get(i);

				if (i % 2 == 0) {
					if (t.getType() != TokenType.WORD && t.getType() != TokenType.QUOTED_WORD) {
						// TODO error
						ULogger.error("unexpected " + t.getValue());
						return;
					}

					// TODO : manage class inheritance
					// this.clazz.
				} else {
					if (t.getType() != TokenType.COMMA) {
						// TODO error
						ULogger.error("missing comma");
						return;
					}
				}
			}

			if (tokenList.size() % 2 == 0) {
				Token lastToken = tokenList.get(tokenList.size() - 1);
				if (lastToken.getType() != TokenType.WORD && lastToken.getType() != TokenType.QUOTED_WORD) {
					// TODO error
					ULogger.error("Unexpected currentToken " + lastToken.getValue());
					return;
				}
			}
		}

		if (currentToken.getType() == TokenType.INTERFACE_INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			while (currentToken.getType() != TokenType.EOF) {
				currentToken = this.nextToken();

				if (currentToken.getType() == TokenType.OPENING_CURLY_BRACKET) {
					break;
				}

				tokenList.add(currentToken);
			}

			for (int i = 0; i < tokenList.size(); i++) {
				Token t = tokenList.get(i);

				if (i % 2 == 0) {
					if (t.getType() != TokenType.WORD && t.getType() != TokenType.QUOTED_WORD) {
						// TODO error
						ULogger.error("unexpected " + t.getValue());
						return;
					}

					// TODO : manage interface inheritance
					// this.clazz.
				} else {
					if (t.getType() != TokenType.COMMA) {
						// TODO error
						ULogger.error("missing comma");
						return;
					}
				}
			}

			if (tokenList.size() % 2 == 0) {
				Token lastToken = tokenList.get(tokenList.size() - 1);
				if (lastToken.getType() != TokenType.WORD && lastToken.getType() != TokenType.QUOTED_WORD) {
					// TODO error
					ULogger.error("Unexpected token " + lastToken.getValue());
					return;
				}
			}
		}

		if (currentToken.getType() != TokenType.OPENING_CURLY_BRACKET) {
			// TODO
			ULogger.error("Missing {");
		}

		System.out.println(this.clazz.getGenerationOrder());
	}

	protected void addComment(Comment comment) {
		this.clazz.addComment(comment);
	}

	protected void addPackage(Package cPackage) {
		this.clazz.setPackage(cPackage);
	}

	private void validateAccessModifier(Token token) throws Exception {
		ClassAccessModifier accessModifier = ClassAccessModifier.getModifierFromOOMLSign(token.getValue());
		if (accessModifier == null) {
			// TODO error
			ULogger.error("invalid accessModifier");
			throw new Exception();
		}

		this.clazz.setAccessModifier(accessModifier);
	}

	private void validateBehaviorModifier(Token token) {
		this.clazz.addBehaviorModifier(new BehaviorModifier(token.getValue()));
	}

}
