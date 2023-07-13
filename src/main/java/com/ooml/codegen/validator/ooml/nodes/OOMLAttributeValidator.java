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
	private void validateAccessModifier() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.SIGN || nextToken.getType() == TokenType.ACCESS_MODIFIER_BLOCK) {
			this.nAttribut.addChild(new LAccessModifierAttribut(nextToken.getValue()));
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateNonAccessModifiers() throws Exception {
		List<Token> beforeColonTokenList = new ArrayList<>();

		Token nextToken = this.nextToken();

		while (nextToken.getType() != TokenType.COLON) {
			beforeColonTokenList.add(nextToken);

			nextToken = this.nextToken();

			if (nextToken.getType() == TokenType.EOF) {
				// TODO :
				ULogger.error("error");
				throw new Exception();
			}
		}

		if (beforeColonTokenList.size() == 0) {
			this.insertToken(nextToken);
			return;
		}

		if (beforeColonTokenList.size() == 1) {
			this.insertToken(nextToken);
			this.insertToken(beforeColonTokenList.get(0));
			return;
		}

		this.insertToken(beforeColonTokenList.get(beforeColonTokenList.size() - 1));
		beforeColonTokenList.remove(beforeColonTokenList.size() - 1);

		this.insertToken(nextToken);

		for (Token token : beforeColonTokenList) {
			this.nAttribut.addChild(new LNonAccessModifier(token.getValue()));
		}
	}

	private void validateName() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.COLON) {
			// TODO
			ULogger.error("Name not found");
			throw new Exception();
		}

		this.nAttribut.addChild(new LName(nextToken.getValue()));

		if (this.nextToken().getType() != TokenType.COLON) {
			// TODO
			ULogger.error("Unexpected token");
			throw new Exception();
		}
	}

	private void validateType() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() == TokenType.EOF) {
			// TODO
			ULogger.error("Missing type");
			throw new Exception();
		}

		this.nAttribut.addChild(new LType(nextToken.getValue()));
	}

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
