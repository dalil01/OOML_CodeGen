package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

	protected final LexerManager lexerManager;
	protected final List<Token> unConsumedTokenList;

	protected Validator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		this.lexerManager = lexerManager;
		this.unConsumedTokenList = new ArrayList<>(unConsumedTokenList);
	}

	public abstract void validate() throws FileNotFoundException;

}
