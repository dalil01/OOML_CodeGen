package com.ooml.codegen.lexer;

import com.ooml.codegen.lexer.Token.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

public abstract class LexerManager {

	protected Deque<Lexer> stack;

	protected final File initialFile;

	private final List<Token> unConsumedTokenList = new ArrayList<>();
	private final LinkedList<Token> nextTokens = new LinkedList<>();

	protected LexerManager(Lexer lexer) {
		this.stack = new ArrayDeque<>();
		this.stack.push(lexer);
		this.initialFile = lexer.getFile();
	}

	public TokenType nextTokenType() throws FileNotFoundException {
		return this.nextTokenHelper(false).getType();
	}

	public TokenType nextTokenType(boolean skipComment) throws FileNotFoundException {
		return this.nextTokenHelper(skipComment).getType();
	}

	public List<Token> consumeTokens() {
		List<Token> tokenList = new ArrayList<>(this.unConsumedTokenList);
		this.unConsumedTokenList.clear();
		return tokenList;
	}

	public void restore() {
		this.nextTokens.addAll(this.unConsumedTokenList);
		this.unConsumedTokenList.clear();
	}

	public void insertTokenBefore(Token token) {
		this.nextTokens.add(0, token);
	}

	public void insertTokensBefore(List<Token> tokenList) {
		this.nextTokens.addAll(0, tokenList);
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

	private Token nextToken() throws FileNotFoundException {
		return this.nextTokenHelper(false);
	}

	private Token nextTokenHelper(boolean skipComment) throws FileNotFoundException {
		Token nextToken;

		if (!this.nextTokens.isEmpty()) {
			nextToken = this.nextTokens.removeFirst();
			this.unConsumedTokenList.add(nextToken);

			if (skipComment && nextToken.isComment()) {
				return this.nextTokenHelper(skipComment);
			}

			return nextToken;
		}

		if (!stack.isEmpty()) {
			nextToken = this.stack.peek().nextToken();

			if (nextToken.getType() == TokenType.EOF) {
				this.stack.pop();
				return this.nextTokenHelper(skipComment);
			}

			if (nextToken.getType() == TokenType.IMPORT) {
				this.manageImport(nextToken);
				return this.nextTokenHelper(skipComment);
			}

			this.unConsumedTokenList.add(nextToken);

			if (skipComment && nextToken.isComment()) {
				return this.nextTokenHelper(skipComment);
			}

			return nextToken;
		}

		nextToken = new Token(TokenType.EOF, this.initialFile.toPath(), 0, 0);
		this.unConsumedTokenList.add(nextToken);

		return nextToken;
	}

}
