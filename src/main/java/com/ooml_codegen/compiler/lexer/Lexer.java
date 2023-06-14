package com.ooml_codegen.compiler.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.stream.Stream;


public abstract class Lexer {

	protected final String filePath;

	protected BufferedReader reader;

	protected Lexer(String filePath) throws FileNotFoundException {
		this.filePath = filePath;
		this.reader = null;
	}

	public abstract Stream<Token> tokenize();

}
