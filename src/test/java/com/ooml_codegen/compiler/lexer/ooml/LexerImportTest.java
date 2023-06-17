package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerImportTest extends LexerTest {

	public LexerImportTest() {
		super("import.ooml");
	}

	@Test
	public void importsTest() {
		this.lexer.tokenize().forEach(token -> {
			int currentToken = this.index.getAndIncrement();

			System.out.println(token.toString());


			if (List.of(1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 22, 23).contains(currentToken)) {
				Assertions.assertEquals(TokenType.IMPORT, token.getType());
			}

			if (currentToken == 1) {
				Assertions.assertEquals("main.ooml", token.getStringValue());
			}
			else if (currentToken == 2) {
				Assertions.assertEquals("../models/models.ooml", token.getStringValue());
			}
			else if (currentToken == 3) {
				Assertions.assertEquals("../../controller/controllers.ooml/", token.getStringValue());
			}
			else if (currentToken == 5) {
				Assertions.assertEquals("All", token.getStringValue());
			}
			else if (currentToken == 6) {
				Assertions.assertEquals("Services/", token.getStringValue());
			}
			else if (currentToken == 7) {
				Assertions.assertEquals("config.ooml", token.getStringValue());
			}
			else if (currentToken == 8) {
				Assertions.assertEquals("/", token.getStringValue());
			}
			else if (currentToken == 9) {
				Assertions.assertEquals("dir.", token.getStringValue());
			}
			else if (currentToken >= 11 && currentToken <= 16) {
				Assertions.assertEquals("", token.getStringValue());
			}
			else if (currentToken == 17) {
				Assertions.assertEquals("babla2", token.getStringValue());
			}
			else if (currentToken == 18) {
				Assertions.assertEquals("colon", token.getStringValue());
			}
			else if (currentToken == 20) {
				Assertions.assertEquals("space", token.getStringValue());
			}
			else if (currentToken == 22) {
				Assertions.assertEquals("comment//not_a_comment", token.getStringValue());
			}
			else if (currentToken == 23) {
				Assertions.assertEquals("comment/*not_a_comment*/", token.getStringValue());
			}
		});
	}

}
