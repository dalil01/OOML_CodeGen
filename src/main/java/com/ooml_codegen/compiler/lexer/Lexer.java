package com.ooml_codegen.compiler.lexer;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;


public abstract class Lexer {

	protected final String filePath;
	protected Scanner scanner;

	protected Lexer(String filePath) {
		this.filePath = filePath;
		this.scanner = null;
	}

	public abstract Stream<Token> tokenize() throws FileNotFoundException;

}
