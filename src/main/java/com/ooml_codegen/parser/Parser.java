package com.ooml_codegen.parser;

import com.ooml_codegen.generator.ICodeGenNode;
import com.ooml_codegen.lexer.LexerManager;

import java.util.stream.Stream;

public abstract class Parser {

	protected final LexerManager lexerManager;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract Stream<ICodeGenNode> parse() throws Exception;

}
