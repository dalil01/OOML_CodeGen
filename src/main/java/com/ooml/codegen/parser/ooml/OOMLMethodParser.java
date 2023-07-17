package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.nodes.MethodModelizer;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OOMLMethodParser extends Parser {

	MethodModelizer methodModelizer = new MethodModelizer();

	public OOMLMethodParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Node parse() throws Exception {
		this.parseAccessModifier();
		this.parseNonAccessModifiers();
		this.parseName();
		this.parseParameters();
		this.parseReturnType();
		this.parseEnd();

		return this.methodModelizer.getModel();
	}

	private void parseAccessModifier() throws Exception {
		Token.TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == Token.TokenType.SIGN || nextTokenType == Token.TokenType.ACCESS_MODIFIER_BLOCK) {
			this.methodModelizer.addAccessModifier(this.lexerManager.consumeTokens());
		} else {
			this.lexerManager.restore();
		}
	}

	private void parseNonAccessModifiers() throws Exception {
		Token.TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		// Sure we have opening parenthesis, otherwise we wouldn't be in attribute parser!
		while (nextTokenType != Token.TokenType.OPENING_PARENTHESIS) {
			nextTokenType = this.lexerManager.nextTokenType(true);
		}

		List<Token> beforeParenthesisTokens = this.lexerManager.consumeTokens();

		// We remove the colon token.
		beforeParenthesisTokens.remove(beforeParenthesisTokens.size() - 1);

		List<Token> nameTokens = new ArrayList<>();
		for (int i = beforeParenthesisTokens.size() - 1; i >= 0; i--) {
			if (beforeParenthesisTokens.get(i).isComment()) {
				nameTokens.add(beforeParenthesisTokens.remove(i));
			} else {
				nameTokens.add(beforeParenthesisTokens.remove(i));
				break;
			}
		}

		Collections.reverse(nameTokens);
		this.lexerManager.insertTokensBefore(nameTokens);

		this.methodModelizer.addNonAccessModifiers(beforeParenthesisTokens);
	}

	private void parseName() throws Exception {
		Token.TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == Token.TokenType.OPENING_PARENTHESIS) {
			// TODO
			ULogger.error("Missing attr Name");
			throw new Exception();
		}

		this.methodModelizer.addName(this.lexerManager.consumeTokens());
	}

	private void parseParameters() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.CLOSING_PARENTHESIS) {
			this.methodModelizer.addComments(this.lexerManager.consumeTokens());
			return;
		}

		while (nextTokenType != TokenType.CLOSING_PARENTHESIS) {
			this.lexerManager.restore();
			this.methodModelizer.getModel().addChild((new OOMLParameterParser(this.lexerManager).parse()));
			nextTokenType = this.lexerManager.nextTokenType(true);
			if (nextTokenType == TokenType.COMMA) {
				this.methodModelizer.addComments(this.lexerManager.consumeTokens());
				nextTokenType = this.lexerManager.nextTokenType(true);
			}
		}
	}

	private void parseReturnType() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType != TokenType.COLON) {
			// TODO
			ULogger.error("Missing colon");
			throw new Exception();
		}

		this.methodModelizer.addComments(this.lexerManager.consumeTokens());

		nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.EOF) {
			// TODO
			ULogger.error("Missing return type");
			throw new Exception();
		}

		this.methodModelizer.addType(this.lexerManager.consumeTokens());
	}

	private void parseEnd() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.SEMI_COLON) {
			for (Token token : this.lexerManager.consumeTokens()) {
				if (token.isComment()) {
					this.methodModelizer.addComment(token);
				}
			}
		} else {
			this.lexerManager.restore();
		}
	}

}
