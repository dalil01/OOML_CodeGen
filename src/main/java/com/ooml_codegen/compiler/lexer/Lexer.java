package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;


public abstract class Lexer {

	protected Deque<SingleLexer> stack;
	protected Lexer(SingleLexer lexer) throws FileNotFoundException {
		this.stack = new ArrayDeque<>();
		stack.push(lexer);
	}

	public abstract Token nextToken() throws FileNotFoundException;

}
