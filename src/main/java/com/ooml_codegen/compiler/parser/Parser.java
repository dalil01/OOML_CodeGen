package com.ooml_codegen.compiler.parser;

import com.ooml_codegen.compiler.lexer.Lexer;

import java.io.FileNotFoundException;

public abstract class Parser {

	protected final Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public abstract void parse() throws FileNotFoundException;

}
