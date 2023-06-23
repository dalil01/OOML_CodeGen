package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.utils.ULogger;

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

		if (nextToken.getType() != TokenType.WORD && nextToken.getType() != TokenType.QUOTED_WORD) {
			ULogger.error("Invalid package statement at " + nextToken.getLocation());
			// TODO: need to throw the error, but need a proper exception class for that
			return;
		}
	}

}
