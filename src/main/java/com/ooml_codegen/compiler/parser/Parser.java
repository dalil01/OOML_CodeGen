package com.ooml_codegen.compiler.parser;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Parser {

	/**
	 * @Deprecated Usage in parser !
	 */
	private final LexerManager lexerManager;

	private final List<Token> mustBeProcessBeforeTokens = new ArrayList<>();

	private Token currentToken;

	public Parser(LexerManager lexerManager) {
		this.lexerManager = lexerManager;
	}

	public abstract Stream<IGeneration> parse() throws Exception;

	public Token nextToken() throws FileNotFoundException {
		if (this.mustBeProcessBeforeTokens.size() > 0) {
			this.currentToken = this.mustBeProcessBeforeTokens.get(0);
			this.mustBeProcessBeforeTokens.remove(0);
		} else {
			this.currentToken = this.lexerManager.nextToken();
		}

		return this.currentToken;
	}

	public Token getCurrentToken() {
		return this.currentToken;
	}

	public LexerManager getLexerManager() {
		return this.lexerManager;
	}

	public void insertTokensBefore(List<Token> tokenList) {
		this.mustBeProcessBeforeTokens.addAll(tokenList);
	}

}
