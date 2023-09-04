package com.ooml.codegen.parser;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.models.Node;

public abstract class Parser {

	protected final LexerManager lexerManager;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract Node parse() throws Exception;

}
