package com.ooml_codegen.compiler.parser;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.lexer.LexerManager;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Parser {

	protected final LexerManager lexerManager;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract Stream<IGeneration> parse() throws Exception;

}
