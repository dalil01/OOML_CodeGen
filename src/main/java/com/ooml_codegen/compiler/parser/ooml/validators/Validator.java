package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.comment.MultiLineComment;
import com.ooml_codegen.models.comment.SingleLineComment;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

	private final LexerManager lexerManager;
	private boolean lexerManagerUsedOnce = false;
	private final List<Token> unConsumedParserTokenList;

	private Token currentToken = null;

	protected final List<Token> unConsumedTokenList = new ArrayList<>();

	protected Validator(LexerManager lexerManager, List<Token> unConsumedParserTokenList) {
		this.lexerManager = lexerManager;
		this.unConsumedParserTokenList = new ArrayList<>(unConsumedParserTokenList);
	}

	public List<Token> getUnConsumedTokenList() {
		return this.unConsumedTokenList;
	}

	public abstract void validate() throws Exception;

	public Token nextToken() throws FileNotFoundException {
		if (this.unConsumedParserTokenList.size() > 0) {
			this.currentToken = this.unConsumedParserTokenList.get(0);
			this.unConsumedParserTokenList.remove(0);

			if (this.currentToken.getType() == TokenType.PACKAGE) {
				// We are sure to have the package name in the following token.
				this.currentToken = this.unConsumedParserTokenList.get(0);
				this.addPackage(new Package(this.currentToken.getValue()));
				this.unConsumedParserTokenList.remove(0);
				this.nextToken();
			}
		} else {
			if (this.lexerManagerUsedOnce) {
				this.currentToken = this.lexerManager.nextToken();
			} else {
				this.currentToken = this.lexerManager.getCurrentToken();
				this.lexerManagerUsedOnce = true;
			}
		}

		this.consumeComment();

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

	protected abstract void addPackage(Package cPackage);

}
