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
				TokenType.INHERITANCE,
				TokenType.COLON,
				TokenType.SEMI_COLON,
				TokenType.EQUAL,
				TokenType.SIGN,
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

	@Test
	public void toStringTest() {
		Assertions.assertEquals("SINGLE_LINE_COMMENT", TokenType.SINGLE_LINE_COMMENT.toString());
		Assertions.assertEquals("MULTI_LINE_COMMENT", TokenType.MULTI_LINE_COMMENT.toString());
		Assertions.assertEquals("IMPORT", TokenType.IMPORT.toString());
		Assertions.assertEquals("INHERITANCE", TokenType.INHERITANCE.toString());
		Assertions.assertEquals("COLON", TokenType.COLON.toString());
		Assertions.assertEquals("SEMI_COLON", TokenType.SEMI_COLON.toString());
		Assertions.assertEquals("EQUAL", TokenType.EQUAL.toString());
		Assertions.assertEquals("SIGN", TokenType.SIGN.toString());
		Assertions.assertEquals("WORD", TokenType.WORD.toString());
		Assertions.assertEquals("COMMA", TokenType.COMMA.toString());
		Assertions.assertEquals("OPENING_PARENTHESIS", TokenType.OPENING_PARENTHESIS.toString());
		Assertions.assertEquals("CLOSING_PARENTHESIS", TokenType.CLOSING_PARENTHESIS.toString());
		Assertions.assertEquals("OPENING_BRACKET", TokenType.OPENING_BRACKET.toString());
		Assertions.assertEquals("CLOSING_BRACKET", TokenType.CLOSING_BRACKET.toString());
		Assertions.assertEquals("OPENING_CURLY_BRACKET", TokenType.OPENING_CURLY_BRACKET.toString());
		Assertions.assertEquals("CLOSING_CURLY_BRACKET", TokenType.CLOSING_CURLY_BRACKET.toString());
		Assertions.assertEquals("EOF", TokenType.EOF.toString());
	}

}
