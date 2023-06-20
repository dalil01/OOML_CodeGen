package com.ooml_codegen.compiler.lexer;

import java.io.BufferedReader;
import java.util.stream.Stream;


public abstract class Lexer {

	protected final String filePath;

	protected BufferedReader reader;

	protected Lexer(String filePath) {
		this.filePath = filePath;
		this.reader = null;
	}

	public abstract Stream<Token> tokenize();

}
