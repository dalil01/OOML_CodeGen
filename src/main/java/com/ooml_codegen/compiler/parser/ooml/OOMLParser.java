package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexer;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.compiler.validator.ValidationType;
import com.ooml_codegen.compiler.validator.ooml.OOMLValidator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class OOMLParser extends Parser {

	private final OOMLValidator validator;

	public OOMLParser(Lexer lexer) {
		super(lexer);
		this.validator = new OOMLValidator();
	}

	@Override
	public void parse() {
		Token token = this.lexer.nextToken();
		while (token.getType() != TokenType.EOF){
			TokenType type = token.getType();
			String value = token.getStringValue();

			switch (type) {
				case IMPORT -> this.parseImport(value);
			}
		}
	}

	private void parseImport(String value) {
		if (this.validator.validate(ValidationType.IMPORT, value)) {
			for (String filePath : this.findFilePathFormImport(value)) {
				try {
					Lexer tmpLexer = new OOMLLexer(filePath);
					Parser tmpParser = new OOMLParser(tmpLexer);
					tmpParser.parse();
				} catch (FileNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private List<String> findFilePathFormImport(String value) {
		List<String> list = new ArrayList<>();

		// TODO : if value is dir | loop | find .ooml file

		return list;
	}
}
