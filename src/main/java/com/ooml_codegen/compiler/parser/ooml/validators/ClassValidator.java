package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClassValidator extends Validator {

	private Class clazz = new Class();

	private boolean accessModifierIsSet = false;

	public ClassValidator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		super(lexerManager, unConsumedTokenList);
	}


	public Class getClassIfIsValid() {
		return this.clazz;
	}

	@Override
	public void validate() throws FileNotFoundException {
		if (this.unConsumedTokenList.size() > 0) {
			Token token = this.unConsumedTokenList.get(0);

			TokenType type = token.getType();
			String value = token.getValue();

			if (type == TokenType.SIGN) {
				this.validateAccessModifier(value);
				this.unConsumedTokenList.remove(0);
			}

			if (this.unConsumedTokenList.size() > 0) {
				this.validateBehaviorModifiers(this.unConsumedTokenList);
			}
		}

		Token classNameToken = this.lexerManager.nextToken();
		if (classNameToken.getType() != TokenType.WORD && classNameToken.getType() != TokenType.QUOTED_WORD) {
			// TODO
			ULogger.error("Missing classname");
			return;
		}

		this.clazz.setName(classNameToken.getValue());

		Token nextToken = this.lexerManager.nextToken();
		System.out.println(nextToken);
		if (nextToken.getType() == TokenType.INHERITANCE) {
			List<Token> tokenList = new ArrayList<>();

			nextToken = this.lexerManager.nextToken();
			while (nextToken.getType() != TokenType.EOF && nextToken.getType() != TokenType.OPENING_CURLY_BRACKET) {
				tokenList.add(nextToken);
				nextToken = this.lexerManager.nextToken();
			}

			for (int i = 0; i < tokenList.size(); i++) {
				Token token = tokenList.get(i);
				TokenType type = token.getType();

				if (i % 2 == 0) {
					if (type != TokenType.WORD && type != TokenType.QUOTED_WORD) {
						// TODO error
						ULogger.error("unexpected " + token.getValue());
						return;
					}

					// TODO : manage relation
					// this.clazz.
				} else {
					if (type != TokenType.COMMA) {
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
					ULogger.error("missing " + lastToken.getValue());
					return;
				}
			}
		}

		if (nextToken.getType() != TokenType.OPENING_CURLY_BRACKET) {
			// TODO
			ULogger.error("Missing {");
		}
	}

	private void validateAccessModifier(String signValue) {
		if (this.accessModifierIsSet) {
			ULogger.error("Unexpected " + signValue);
			return;
		}

		ClassAccessModifier accessModifier = ClassAccessModifier.getModifierFromOOMLSign(signValue);
		if (accessModifier == null) {
			// TODO error
			ULogger.error("invalid accessModifier");
			return;
		}

		clazz.setAccessModifier(accessModifier);
		this.accessModifierIsSet = true;
	}

	private void validateBehaviorModifiers(List<Token> tokenList) {
		// TODO
	}

}
