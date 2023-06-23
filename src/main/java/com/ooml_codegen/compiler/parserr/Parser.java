package com.ooml_codegen.compiler.parserr;

import com.ooml_codegen.compiler.lexer.LexerManager;

import java.io.FileNotFoundException;

public abstract class Parser {

	protected final LexerManager lexerManager;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract void parse() throws FileNotFoundException;

}
