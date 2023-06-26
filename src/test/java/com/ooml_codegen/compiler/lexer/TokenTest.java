package com.ooml_codegen.compiler.lexer;

import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.util.Optional;

public class TokenTest {

	@Test
	public void constructorWithTypeTest() {
		TokenType type = TokenType.IMPORT;
		Token token = new Token(type, Path.of(""), 0, 0);

		Assertions.assertEquals(type, token.getType());
		Assertions.assertEquals(Optional.empty(), token.getValue());
		Assertions.assertEquals("", token.getValue());
	}

	@Test
	public void constructorWithTypeAndValueTest() {
		TokenType type = TokenType.SEMI_COLON;
		String value = ";";
		Path path = Path.of("/test/test.ooml");
		Token token = new Token(type, value, path, 5, 6);

		Assertions.assertEquals(type, token.getType());
		Assertions.assertEquals(Optional.of(value), token.getValue());
		Assertions.assertEquals(value, token.getValue());
		Assertions.assertEquals(path, token.getFilePath());
		Assertions.assertEquals(5, token.getLineN());
		Assertions.assertEquals(6, token.getCharN());


	}

	@Test
	public void getTypeTest() {
		TokenType type = TokenType.SIGN;
		Path path = Path.of("/test/test.ooml");
		Token token = new Token(type, path, 5, 6);

		Assertions.assertEquals(type, token.getType());
	}

	@Test
	public void getValueTest() {
		TokenType type = TokenType.SINGLE_LINE_COMMENT;
		String value = "123";
		Path path = Path.of("/test/test.ooml");
		Token token = new Token(type, value, path, 5, 6);

		Assertions.assertEquals(Optional.of(value), token.getValue());
	}

	@Test
	public void getStringValueTest() {
		TokenType type = TokenType.WORD;
		String value = "Hello, World!";
		Path path = Path.of("/test/test.ooml");
		Token token = new Token(type, value, path, 5, 6);

		Assertions.assertEquals(value, token.getValue());
	}

	@Test
	public void toStringTest() {
		TokenType type = TokenType.WORD;
		String value = "if";
		Path path = Path.of("/test/test.ooml");
		Token token = new Token(type, value, path, 5, 6);

		String expectedString = "Token{" +
				"type=" + type +
				", value=" + value +
				", file=" + path.toString() +
				", line=5, char=6" +
				'}';

		Assertions.assertEquals(expectedString, token.toString());
	}

}
