package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;

import java.io.FileNotFoundException;

public class OOMLParser extends Parser {


	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
	}

	@Override
	public void parse() throws FileNotFoundException {
		Token token = this.lexerManager.nextToken();

		while (token.getType() != TokenType.EOF){
			TokenType type = token.getType();
			String value = token.getStringValue();

			System.out.println("PARSER: " + token);

			switch (type) {
				case PACKAGE -> this.parsePackage();
			}

			token = this.lexerManager.nextToken();
		}
	}

	private void parsePackage() throws FileNotFoundException {
		Token nextToken = this.lexerManager.nextToken();

		if (nextToken.getType() != TokenType.WORD) {
			// TODO mange error
			return;
		}
	}

}
