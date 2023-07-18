package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.ModelizerFactory;
import com.ooml.codegen.modelizer.all.IAttributMlz;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OOMLAttributeParser extends Parser {

	private final IAttributMlz attributModelizer = ModelizerFactory.createAttribut();

	public OOMLAttributeParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Node parse() throws Exception {
		this.parseAccessModifier();
		this.parseNonAccessModifiers();
		this.parseName();
		this.parseType();
		this.parseDefaultValue();
		this.parseEnd();

		return this.attributModelizer.getModel();
	}

	private void parseAccessModifier() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.SIGN || nextTokenType == TokenType.ACCESS_MODIFIER_BLOCK) {
			this.attributModelizer.addAttributAccessModifier(this.lexerManager.consumeTokens());
		} else {
			this.lexerManager.restore();
		}
	}

	private void parseNonAccessModifiers() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		// Sure we have colon, otherwise we wouldn't be in attribute parser!
		while (nextTokenType != TokenType.COLON) {
			nextTokenType = this.lexerManager.nextTokenType(true);
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

		this.attributModelizer.addNonAccessModifiers(beforeColonTokens);
	}

	private void parseName() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.COLON) {
			// TODO
			ULogger.error("Missing attr Name");
			throw new Exception();
		}

		this.attributModelizer.addName(this.lexerManager.consumeTokens());
	}

	private void parseType() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.EOF) {
			// TODO
			ULogger.error("Missing attr type");
			throw new Exception();
		}

		this.attributModelizer.addType(this.lexerManager.consumeTokens());
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

		this.attributModelizer.addValue(tokenList);
	}

	private void parseEnd() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.SEMI_COLON) {
			for (Token token : this.lexerManager.consumeTokens()) {
				if (token.isComment()) {
					this.attributModelizer.addComment(token);
				}
			}
		} else {
			this.lexerManager.restore();
		}
	}

}
