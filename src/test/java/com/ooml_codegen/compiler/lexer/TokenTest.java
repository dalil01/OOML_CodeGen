package com.ooml_codegen.compiler.lexer;

import org.junit.jupiter.api.*;
import java.util.Optional;

public class TokenTest {

	@Test
	public void constructorWithTypeTest() {
		TokenType type = TokenType.IMPORT;
		Token token = new Token(type);

		Assertions.assertEquals(type, token.getType());
		Assertions.assertEquals(Optional.empty(), token.getValue());
		Assertions.assertEquals("", token.getStringValue());
	}

	@Test
	public void constructorWithTypeAndValueTest() {
		TokenType type = TokenType.SEMI_COLON;
		String value = ";";
		Token token = new Token(type, value);

		Assertions.assertEquals(type, token.getType());
		Assertions.assertEquals(Optional.of(value), token.getValue());
		Assertions.assertEquals(value, token.getStringValue());
	}

	@Test
	public void getTypeTest() {
		TokenType type = TokenType.SIGN;
		Token token = new Token(type);

		Assertions.assertEquals(type, token.getType());
	}

	@Test
	public void getValueTest() {
		TokenType type = TokenType.SINGLE_LINE_COMMENT;
		String value = "123";
		Token token = new Token(type, value);

		Assertions.assertEquals(Optional.of(value), token.getValue());
	}

	@Test
	public void getStringValueTest() {
		TokenType type = TokenType.WORD;
		String value = "Hello, World!";
		Token token = new Token(type, value);

		Assertions.assertEquals(value, token.getStringValue());
	}

	@Test
	public void toStringTest() {
		TokenType type = TokenType.WORD;
		String value = "if";
		Token token = new Token(type, value);

		String expectedString = "Token{" +
				"type=" + type +
				", value=" + value +
				'}';

		Assertions.assertEquals(expectedString, token.toString());
	}

}
