package com.ooml.codegen.validator;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;

import java.util.List;

public abstract class Validator {

	private final LexerManager lexerManager;

	public Validator(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract void validate() throws Exception;

	public abstract Token nextToken() throws Exception;

	public void insertToken(Token token) {
		this.lexerManager.insertToken(token);
	}

	public void insertTokens(List<Token> tokens) {
		this.lexerManager.insertTokens(tokens);
	}

}
