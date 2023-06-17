package com.ooml_codegen.compiler.parser;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.validator.Validator;

public abstract class Parser {

	protected final Lexer lexer;

	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}

	public abstract void parse();

}
