package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;


public abstract class LexerManager {

	protected Deque<Lexer> stack;

	protected final File initialFile;

	protected LexerManager(Lexer lexer) {
		this.stack = new ArrayDeque<>();
		stack.push(lexer);
		this.initialFile = lexer.getFile();
	}

	public Token nextToken() throws FileNotFoundException{
		if (!stack.isEmpty()) {
			Token t = this.stack.peek().nextToken();

			if (t.getType() == TokenType.EOF) {
				this.stack.pop();
				return this.nextToken();
			}

			if (t.getType() == TokenType.IMPORT) {
				this.manageImport(t);
				return this.nextToken();
			}

			return t;
		}

		return new Token(TokenType.EOF, this.initialFile.toPath(), 0, 0);
	}

	protected abstract void manageImport(Token token) throws FileNotFoundException;

}
