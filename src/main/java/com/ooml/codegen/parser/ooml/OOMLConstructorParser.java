package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.ModelizerFactory;
import com.ooml.codegen.modelizer.all.IConstructorModelizer;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.ULogger;

public class OOMLConstructorParser extends Parser {

	private final IConstructorModelizer constructorModelizer = ModelizerFactory.createConstructor();

	public OOMLConstructorParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Node parse() throws Exception {
		this.parseAccessModifier();
		this.parseName();
		this.parseParameters();
		this.parseEnd();

		return this.constructorModelizer.getModel();
	}

	private void parseAccessModifier() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.SIGN || nextTokenType == TokenType.ACCESS_MODIFIER_BLOCK) {
			this.constructorModelizer.addConstructorAccessModifier(this.lexerManager.consumeTokens());
		} else {
			this.lexerManager.restore();
		}
	}

	private void parseName() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType != TokenType.OPENING_PARENTHESIS) {
			this.constructorModelizer.addName(this.lexerManager.consumeTokens());
		} else {
			this.lexerManager.restore();
		}
	}

	private void parseParameters() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		System.out.println(nextTokenType);
		if (nextTokenType != TokenType.OPENING_PARENTHESIS) {
			// TODO
			ULogger.error("unexpected token ");
			throw new Exception();
		} else {
			this.constructorModelizer.addComments(this.lexerManager.consumeTokens());
		}

		nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.CLOSING_PARENTHESIS) {
			this.constructorModelizer.addComments(this.lexerManager.consumeTokens());
			return;
		}

		while (nextTokenType != TokenType.CLOSING_PARENTHESIS) {
			this.lexerManager.restore();
			this.constructorModelizer.getModel().addChild((new OOMLParameterParser(this.lexerManager).parse()));
			nextTokenType = this.lexerManager.nextTokenType(true);
			if (nextTokenType == TokenType.COMMA) {
				this.constructorModelizer.addComments(this.lexerManager.consumeTokens());
				nextTokenType = this.lexerManager.nextTokenType(true);
			}
		}
	}

	private void parseEnd() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.SEMI_COLON) {
			for (Token token : this.lexerManager.consumeTokens()) {
				if (token.isComment()) {
					this.constructorModelizer.addComment(token);
				}
			}
		} else {
			this.lexerManager.restore();
		}
	}

}
