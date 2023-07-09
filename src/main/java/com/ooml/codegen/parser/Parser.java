package com.ooml.codegen.parser;

import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.lexer.LexerManager;

import java.util.stream.Stream;

public abstract class Parser {

	protected final LexerManager lexerManager;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract Stream<ICodeGenNode> parse() throws Exception;

}
