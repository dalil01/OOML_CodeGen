package com.ooml.codegen.validator.ooml;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.models.nodes.leafs.LCommentMultiLine;
import com.ooml.codegen.models.nodes.leafs.LCommentSingleLine;
import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.validator.Validator;
import com.ooml.codegen.validator.ooml.nodes.OOMLAttributeValidator;
import com.ooml.codegen.validator.ooml.nodes.OOMLConstructorValidator;
import com.ooml.codegen.validator.ooml.nodes.OOMLMethodValidator;

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

	protected abstract void addComment(LComment comment);

	protected OOMLAttributeValidator newAttributValidator() {
		return new OOMLAttributeValidator(this.lexerManager);
	}

	protected OOMLConstructorValidator newConstructorValidator() {
		return new OOMLConstructorValidator(this.lexerManager);
	}

	protected OOMLMethodValidator newMethodValidator() {
		return new OOMLMethodValidator(this.lexerManager);
	}

	private void consumeComment() throws Exception {
		TokenType type = this.currentToken.getType();
		while (type == TokenType.SINGLE_LINE_COMMENT || type == TokenType.MULTI_LINE_COMMENT) {
			String value = this.currentToken.getValue();
			if (type == TokenType.SINGLE_LINE_COMMENT) {
				this.addComment(new LCommentSingleLine(value));
			} else {
				this.addComment(new LCommentMultiLine(value));
			}

			this.nextToken();
			type = this.currentToken.getType();

			if (type == TokenType.EOF) {
				break;
			}
		}
	}

}
