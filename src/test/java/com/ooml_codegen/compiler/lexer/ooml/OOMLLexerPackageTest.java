package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class OOMLLexerPackageTest extends OOMLLexerTest {

	public OOMLLexerPackageTest() {
		super("package.ooml");
	}

	@Test
	public void packageTest() throws FileNotFoundException {
		this.lexer.tokenize().forEach(token -> {
			int currentLine = this.index.getAndIncrement();

			//System.out.println(token.toString());

			/*
			if (List.of(1, 2, 3, 5, 7).contains(currentLine)) {
				Assertions.assertEquals(TokenType.PACKAGE, token.type());
			}

			if (currentLine == 1) {
				Assertions.assertEquals("", token.value());
			}
			else if (currentLine == 2) {
				Assertions.assertEquals("com.ooml.models", token.value());
			}
			else if (currentLine == 3) {
				Assertions.assertEquals("com.ooml.compiler", token.value());
			}
			else if (currentLine == 5) {
				Assertions.assertEquals("com.ooml.utils", token.value());
			}
			else if (currentLine == 7) {
				Assertions.assertEquals("com.ooml.models.enums", token.value());
			}

			 */
		});
	}

}
