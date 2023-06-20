package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexer;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.compiler.validator.ooml.OOMLValidator;

import java.io.FileNotFoundException;

public class OOMLParser extends Parser {

	private final OOMLValidator validator;

	public OOMLParser(OOMLLexer lexer) {
		super(lexer);
		this.validator = new OOMLValidator();
	}

	@Override
	public void parse() throws FileNotFoundException {
		Token token = this.lexer.nextToken();
		while (token.getType() != TokenType.EOF){
			TokenType type = token.getType();
			String value = token.getStringValue();

			switch (type) {
			}
		}
	}

}
