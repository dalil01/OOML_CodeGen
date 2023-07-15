package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.validator.ooml.OOMLValidator;
import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.models.nodes.NAttribut;
import com.ooml.codegen.utils.UContextStack;

import java.util.ArrayList;
import java.util.List;

public class OOMLAttributeValidator extends OOMLValidator {

	private final NAttribut nAttribut = new NAttribut();

	public OOMLAttributeValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NAttribut getValidatedNode() {
		return this.nAttribut;
	}

	@Override
	public void validate() throws Exception {

		/*this.validateAccessModifier();
		this.validateNonAccessModifiers();
		this.validateName();
		this.validateType();
		this.validateDefaultValue();

		Token nextToken = this.nextToken();
		if (nextToken.getType() != TokenType.SEMI_COLON) {
			this.insertToken(nextToken);
		}

		 */
	}

	/*

	private void validateDefaultValue() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() != TokenType.EQUAL) {
			this.insertToken(nextToken);
			return;
		}

		nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.OPENING_PARENTHESIS) {
			StringBuilder s = new StringBuilder();

			UContextStack internContext = new UContextStack();

			nextToken = this.nextToken();
			while (!internContext.empty() || nextToken.getType() != TokenType.CLOSING_PARENTHESIS) {
				if (nextToken.getType() == TokenType.WORD || nextToken.getType() == TokenType.QUOTED_WORD) {
					s.append(' ');
				}
				s.append(nextToken.getValue());

				if (nextToken.getType() == TokenType.OPENING_PARENTHESIS) {
					internContext.push(UContextStack.ContextType.PARENTHESIS);
				} else if (nextToken.getType() == TokenType.CLOSING_PARENTHESIS) {
					internContext.pop();
				}

				nextToken = this.nextToken();

				if (nextToken.getType() == TokenType.EOF) {
					// TODO
					ULogger.error("Missing )");
					throw new Exception();
				}
			}

			this.nAttribut.addChild(new LValue(s.toString().trim()));
		} else {
			this.nAttribut.addChild(new LValue(nextToken.getValue()));
		}
	}

	 */

	public void addChildren(List<Token> tokenList) throws Exception {

	}

}
