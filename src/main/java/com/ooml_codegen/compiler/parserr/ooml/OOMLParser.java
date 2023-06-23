package com.ooml_codegen.compiler.parserr.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parserr.Parser;
import com.ooml_codegen.compiler.validator.ValidationType;
import com.ooml_codegen.compiler.validator.ooml.OOMLValidator;

import java.io.FileNotFoundException;

public class OOMLParser extends Parser {

	private final OOMLValidator validator;


	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
		this.validator = new OOMLValidator();
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

		if (this.validator.validate(ValidationType.PACKAGE, nextToken.getValue())) {
			// TODO : Send order to Model builder to set package
		}
	}

}
