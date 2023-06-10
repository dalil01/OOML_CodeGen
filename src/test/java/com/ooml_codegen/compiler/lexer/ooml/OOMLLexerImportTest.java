package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class OOMLLexerImportTest extends OOMLLexerTest {

	public OOMLLexerImportTest() {
		super("imports.ooml");
	}

	@Test
	public void importsTest() throws FileNotFoundException {
		this.lexer.tokenize().forEach(token -> {
			int currentLine = this.index.getAndIncrement();
			System.out.println(token.toString());

			Assertions.assertEquals(TokenType.IMPORT, token.type());

			if (currentLine == 1) {
				Assertions.assertEquals("main.ooml", token.value());
			}
			else if (currentLine == 2) {
				Assertions.assertEquals("../models/models.ooml", token.value());
			}
			else if (currentLine == 3) {
				Assertions.assertEquals("../../controller/controllers.ooml/", token.value());
			}
			else if (currentLine == 4) {
				Assertions.assertEquals("All @Services.ooml", token.value());
			}
			else if (currentLine == 5) {
				Assertions.assertEquals("config.ooml", token.value());
			}
			else if (currentLine == 6) {
				Assertions.assertEquals("/", token.value());
			}
			else if (currentLine == 7) {
				Assertions.assertEquals("dir./*", token.value());
			}
			else if (currentLine == 8) {
				Assertions.assertEquals("babla.txt", token.value());
			}
			else if (currentLine == 9) {
				Assertions.assertEquals("@@@@@@babla", token.value());
			}
			else if (currentLine == 10) {
				Assertions.assertEquals("", token.value());
			}
		});
	}

}
