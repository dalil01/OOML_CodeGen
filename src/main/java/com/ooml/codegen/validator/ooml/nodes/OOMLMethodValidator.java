package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.nodes.NMethod;
import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.ArrayList;
import java.util.List;

public class OOMLMethodValidator extends OOMLValidator {

	private final NMethod nMethod = new NMethod();

	private OOMLParameterValidator oomlParameterValidator;

	public OOMLMethodValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NMethod getValidatedNode() {
		return this.nMethod;
	}

	@Override
	public void validate() throws Exception {
		this.validateAccessModifier();
//		this.validateNonAccessModifiers();
//		this.validateName();
//		this.validateParameters();
//		this.validateType();

		Token nextToken = this.nextToken();
		if (nextToken.getType() != Token.TokenType.SEMI_COLON) {
			this.insertToken(nextToken);
		}
	}

	private void validateAccessModifier() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() == Token.TokenType.SIGN || nextToken.getType() == Token.TokenType.ACCESS_MODIFIER_BLOCK) {
			this.nMethod.addChild(new LAccessModifierMethod(nextToken.getValue()));
		} else {
			this.insertToken(nextToken);
		}
	}


	private void validateNonAccessModifiers() throws Exception {
		List<Token> beforeOpenParenthesisTokenList = new ArrayList<>();

		Token nextToken = this.nextToken();

		while (nextToken.getType() != Token.TokenType.OPENING_PARENTHESIS) {
			beforeOpenParenthesisTokenList.add(nextToken);

			nextToken = this.nextToken();

			if (nextToken.getType() == Token.TokenType.EOF) {
				// TODO :
				ULogger.error("error");
				throw new Exception();
			}
		}

		if (beforeOpenParenthesisTokenList.size() == 0) {
			this.insertToken(nextToken);
			return;
		}

		if (beforeOpenParenthesisTokenList.size() == 1) {
			this.insertToken( beforeOpenParenthesisTokenList.get(0));
			this.insertToken(nextToken);
			return;
		}

		this.insertToken(beforeOpenParenthesisTokenList.get(beforeOpenParenthesisTokenList.size() - 1));
		beforeOpenParenthesisTokenList.remove(beforeOpenParenthesisTokenList.size() - 1);

		this.insertToken(nextToken);

		for (Token token : beforeOpenParenthesisTokenList) {
			this.nMethod.addChild(new LNonAccessModifier(token.getValue()));
		}
	}

	private void validateName() throws Exception {
		Token nextToken = this.nextToken();

		System.out.println(nextToken);

		if (nextToken.getType() == Token.TokenType.COLON) {
			// TODO
			ULogger.error("Name not found");
			throw new Exception();
		}

		this.nMethod.addChild(new LName(nextToken.getValue()));

		if (this.nextToken().getType() != Token.TokenType.OPENING_PARENTHESIS) {
			// TODO
			ULogger.error("Unexpected token");
			throw new Exception();
		}
	}


	private void validateParameters() throws Exception {

		Token nextToken = this.nextToken();

		while (nextToken.getType() != Token.TokenType.CLOSING_PARENTHESIS) {
			if (nextToken.getType() != Token.TokenType.EOF) {
				// TODO
				ULogger.error("Missing token ");
				throw new Exception();
			}

			nextToken = this.nextToken();
		}

		this.insertToken(nextToken);

	}


	private void validateType() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() == Token.TokenType.EOF) {
			// TODO
			ULogger.error("Missing type");
			throw new Exception();
		}

		this.nMethod.addChild(new LType(nextToken.getValue()));
	}

	@Override
	protected void addComment(LComment comment) {
		this.nMethod.addChild(comment);
	}

}
