package com.ooml_codegen.compiler.lexer;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;


public abstract class LexerManager {

	protected Deque<Lexer> stack;

	protected LexerManager(Lexer lexer) {
		this.stack = new ArrayDeque<>();
		stack.push(lexer);
	}

	public Token nextToken() throws FileNotFoundException{
		if (!stack.isEmpty()){
			Token tok = this.stack.peek().nextToken();

			while (tok.getType() == TokenType.EOF){
				this.stack.pop();

				if (this.stack.isEmpty()) {
					break;
				} else {
					tok = this.stack.peek().nextToken();
				}
			}

			if (tok.getType() == TokenType.IMPORT){
				manageImport(tok);
				return nextToken();
			}

			return tok;
		}

		return new Token(TokenType.EOF);
	}

	protected abstract void manageImport(Token token) throws FileNotFoundException;

}
