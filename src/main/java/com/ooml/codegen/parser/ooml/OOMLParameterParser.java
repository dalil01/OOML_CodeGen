package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.ModelizerFactory;
import com.ooml.codegen.modelizer.all.IParameterMlz;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OOMLParameterParser extends Parser {

	private final IParameterMlz parameterModelizer = ModelizerFactory.createParameter();

	public OOMLParameterParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Node parse() throws Exception {
		this.parseNonAccessModifiers();
		this.parseName();
		this.parseType();
		this.parseDefaultValue();

		return this.parameterModelizer.getModel();
	}

	private void parseNonAccessModifiers() throws Exception {
		Token nextToken = this.lexerManager.nextToken(true);
		while (nextToken.getType() != TokenType.COLON) {
			if (nextToken.getType() == TokenType.EOF) {
				// TODO
				ULogger.error("unexpected token ");
				throw new Exception();
			}

			nextToken = this.lexerManager.nextToken(true);
		}

		List<Token> beforeColonTokens = this.lexerManager.consumeTokens();

		// We remove the colon token.
		beforeColonTokens.remove(beforeColonTokens.size() - 1);

		List<Token> nameTokens = new ArrayList<>();
		for (int i = beforeColonTokens.size() - 1; i >= 0; i--) {
			if (beforeColonTokens.get(i).isComment()) {
				nameTokens.add(beforeColonTokens.remove(i));
			} else {
				nameTokens.add(beforeColonTokens.remove(i));
				break;
			}
		}

		Collections.reverse(nameTokens);
		this.lexerManager.insertTokensBefore(nameTokens);

		this.parameterModelizer.addNonAccessModifiers(beforeColonTokens);
	}

	private void parseName() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.COLON) {
			// TODO
			ULogger.error("Missing attr Name");
			throw new Exception();
		}

		this.parameterModelizer.addName(this.lexerManager.consumeTokens());
	}

	private void parseType() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.EOF) {
			// TODO
			ULogger.error("Missing attr type");
			throw new Exception();
		}

		this.parameterModelizer.addType(this.lexerManager.consumeTokens());
	}

	private void parseDefaultValue() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType != TokenType.EQUAL) {
			this.lexerManager.restore();
			return;
		}

		this.lexerManager.nextTokenType(true);

		List<Token> tokenList = this.lexerManager.consumeTokens();
		for (int i = 0; i < tokenList.size(); i++) {
			Token token = tokenList.get(i);
			if (token.getType() == TokenType.EQUAL) {
				tokenList.remove(i);
				break;
			}
		}

		this.parameterModelizer.addValue(tokenList);
	}

}
