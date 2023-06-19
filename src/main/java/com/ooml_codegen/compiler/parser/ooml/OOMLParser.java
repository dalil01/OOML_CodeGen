package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.compiler.validator.ooml.OOMLValidator;

public class OOMLParser extends Parser {

	private final OOMLValidator validator;

	public OOMLParser(Lexer lexer) {
		super(lexer);
		this.validator = new OOMLValidator();
	}

	@Override
	public void parse() {
		this.lexer.tokenize().forEach(token -> {
			TokenType type = token.getType();
			String value = token.getStringValue();

			switch (type) {
			}
		});
	}

}
