package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.comment.MultiLineComment;
import com.ooml_codegen.models.comment.SingleLineComment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

	private final LexerManager lexerManager;
	private boolean lexerManagerUsedOnce = false;
	private final List<Token> unConsumedTokenList;

	private Token currentToken = null;

	protected Validator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		this.lexerManager = lexerManager;
		this.unConsumedTokenList = new ArrayList<>(unConsumedTokenList);
	}

	public Token getCurrentToken() {
		return this.currentToken;
	}

	public abstract void validate() throws FileNotFoundException;

	public Token nextToken() throws FileNotFoundException {
		if (this.unConsumedTokenList.size() > 0) {
			this.currentToken = this.unConsumedTokenList.get(0);
			this.unConsumedTokenList.remove(0);
		} else {
			if (this.lexerManagerUsedOnce) {
				this.currentToken = this.lexerManager.nextToken();
			} else {
				this.currentToken = this.lexerManager.getCurrentToken();
				this.lexerManagerUsedOnce = true;
			}
		}

		this.consumeComment();

		System.out.println("Next token" + this.currentToken);

		return this.currentToken;
	}

	private void consumeComment() throws FileNotFoundException {
		TokenType type = this.currentToken.getType();
		while (type == TokenType.SINGLE_LINE_COMMENT || type == TokenType.MULTI_LINE_COMMENT) {
			String value = this.currentToken.getValue();
			if (type == TokenType.SINGLE_LINE_COMMENT) {
				this.addComment(new SingleLineComment(value));
			} else {
				this.addComment(new MultiLineComment(value));
			}

			this.nextToken();
			type = this.currentToken.getType();

			if (type == TokenType.EOF) {
				break;
			}
		}
	}

	protected abstract void addComment(Comment comment);

}
