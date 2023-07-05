package com.ooml_codegen.validator.ooml;

import com.ooml_codegen.lexer.LexerManager;
import com.ooml_codegen.lexer.Token;
import com.ooml_codegen.lexer.TokenType;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.comment.MultiLineComment;
import com.ooml_codegen.models.comment.SingleLineComment;
import com.ooml_codegen.validator.Validator;
import com.ooml_codegen.validator.ooml.nodes.OOMLAttributeValidator;

public abstract class OOMLValidator extends Validator {

	private final LexerManager lexerManager;

	private Token currentToken = null;

	public OOMLValidator(LexerManager lexerManager) {
		super(lexerManager);
		this.lexerManager = lexerManager;
	}

	public Token nextToken() throws Exception {
		this.currentToken = this.lexerManager.nextToken();

		this.consumeComment();

		return this.currentToken;
	}

	protected abstract void addComment(Comment comment);

	protected OOMLAttributeValidator newAttributValidator() {
		return new OOMLAttributeValidator(this.lexerManager);
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
