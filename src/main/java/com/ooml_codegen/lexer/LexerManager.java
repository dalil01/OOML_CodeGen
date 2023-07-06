package com.ooml_codegen.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

public abstract class LexerManager {

	protected Deque<Lexer> stack;

	protected final File initialFile;

	protected Token currentToken;

	private final LinkedList<Token> unConsumedTokenList = new LinkedList<>();

	protected LexerManager(Lexer lexer) {
		this.stack = new ArrayDeque<>();
		stack.push(lexer);
		this.initialFile = lexer.getFile();
	}

	public Token getCurrentToken() throws FileNotFoundException {
		return this.currentToken == null ? this.nextToken() : this.currentToken;
	}

	public Token nextToken() throws FileNotFoundException{
		for (Token token : this.unConsumedTokenList) {
			if (token.getType() == TokenType.IMPORT) {
				this.manageImport(token);
				return this.nextToken();
			}

			this.currentToken = token;
			this.unConsumedTokenList.removeFirst();

			return this.currentToken;
		}

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

			this.currentToken = t;

			return t;
		}

		return new Token(TokenType.EOF, this.initialFile.toPath(), 0, 0);
	}

	public void insertToken(Token token) {
		this.unConsumedTokenList.add(token);
	}

	public void insertTokens(List<Token> tokens) {
		this.unConsumedTokenList.addAll(tokens);
	}

	public void forEach(BiConsumer<Integer, Token> action) throws FileNotFoundException {
		Token token = this.nextToken();

		int i = 1;
		while (token.getType() != TokenType.EOF) {
			action.accept(i, token);
			token = this.nextToken();
			i++;
		}
	}

	protected abstract void manageImport(Token token) throws FileNotFoundException;

}
