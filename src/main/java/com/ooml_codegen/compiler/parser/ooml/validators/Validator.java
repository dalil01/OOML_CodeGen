package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.comment.MultiLineComment;
import com.ooml_codegen.models.comment.SingleLineComment;

import java.util.List;

public abstract class Validator {

	private final LexerManager lexerManager;

	private Token currentToken = null;

	public Validator(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract void validate() throws Exception;

	public Token nextToken() throws Exception {
		this.currentToken = this.lexerManager.nextToken();

		this.consumeComment();

		return this.currentToken;
	}

	public void insertToken(Token token) {
		this.lexerManager.insertToken(token);
	}

	public void insertTokens(List<Token> tokens) {
		this.lexerManager.insertTokens(tokens);
	}

	protected abstract void addComment(Comment comment);

	protected AttributeValidator newAttributValidator() {
		return new AttributeValidator(this.lexerManager);
	}

	private void consumeComment() throws Exception {
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

}
