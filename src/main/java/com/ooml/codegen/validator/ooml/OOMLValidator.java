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

import java.util.ArrayList;
import java.util.List;

public abstract class OOMLValidator extends Validator {

	private final LexerManager lexerManager;

	private Token currentToken = null;

	private final List<Token> unConsumedComments = new ArrayList<>();

	public OOMLValidator(LexerManager lexerManager) {
		super(lexerManager);
		this.lexerManager = lexerManager;
	}

	public Token nextToken() throws Exception {
		return this.nextTokenHelper(true);
	}

	public Token nextToken(boolean consumeComment) throws Exception {
		return this.nextTokenHelper(consumeComment);
	}

	public Token nextTokenHelper(boolean consumeComment) throws Exception {
		if (consumeComment) {
			this.addComments();
		}

		this.currentToken = this.lexerManager.nextToken();

		if (consumeComment) {
			this.consumeComment();
		}

		return this.currentToken;
	}

	public void insertToken(Token token) {
		this.addComments();
		super.insertToken(token);
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
			this.unConsumedComments.add(this.currentToken);
			type = this.nextToken().getType();
			if (type == TokenType.EOF) {
				break;
			}
		}
	}

	private void addComments() {
		for (Token token : this.unConsumedComments) {
			if (token.getType() == TokenType.SINGLE_LINE_COMMENT) {
				this.addComment(new LCommentSingleLine(token.getValue()));
			} else {
				this.addComment(new LCommentMultiLine(token.getValue()));
			}
		}

		this.unConsumedComments.clear();
	}

}
