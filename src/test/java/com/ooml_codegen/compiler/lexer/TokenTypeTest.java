package com.ooml_codegen.compiler.lexer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TokenTypeTest {

	@Test
	public void valuesTest() {
		TokenType[] expectedValues = {
				TokenType.SINGLE_LINE_COMMENT,
				TokenType.MULTI_LINE_COMMENT,
				TokenType.IMPORT,
				TokenType.PACKAGE,
				TokenType.CLASS,
				TokenType.ENUM,
				TokenType.INTERFACE,
				TokenType.CLASS_INHERITANCE,
				TokenType.COLON,
				TokenType.SEMI_COLON,
				TokenType.EQUAL,
				TokenType.SIGN,
				TokenType.QUOTED_WORD,
				TokenType.WORD,
				TokenType.COMMA,
				TokenType.OPENING_PARENTHESIS,
				TokenType.CLOSING_PARENTHESIS,
				TokenType.OPENING_BRACKET,
				TokenType.CLOSING_BRACKET,
				TokenType.OPENING_CURLY_BRACKET,
				TokenType.CLOSING_CURLY_BRACKET,
				TokenType.EOF
		};

		TokenType[] actualValues = TokenType.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

}
