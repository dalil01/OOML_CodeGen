package com.ooml_codegen.compiler.parser;

import com.ooml_codegen.compiler.lexer.Lexer;

public abstract class Parser {

	protected final Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public abstract void parse();

}
