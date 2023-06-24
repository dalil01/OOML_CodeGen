package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerImportTest extends LexerTest {

	public LexerImportTest() {
		super("import.ooml");
	}

	@Test
	public void importsTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22).contains(i)) {
				Assertions.assertEquals(TokenType.IMPORT, token.getType());
			}

			if (i == 1) {
				Assertions.assertEquals("main.ooml", token.getStringValue());
			}
			else if (i == 2) {
				Assertions.assertEquals("../models/models.ooml", token.getStringValue());
			}
			else if (i == 3) {
				Assertions.assertEquals("../../controller/controllers.ooml/", token.getStringValue());
			}
			else if (i == 5) {
				Assertions.assertEquals("All", token.getStringValue());
			}
			else if (i == 6) {
				Assertions.assertEquals("Services/", token.getStringValue());
			}
			else if (i == 7) {
				Assertions.assertEquals("config.ooml", token.getStringValue());
			}
			else if (i == 8) {
				Assertions.assertEquals("/", token.getStringValue());
			}
			else if (i == 9) {
				Assertions.assertEquals("dir.", token.getStringValue());
			}
			else if (i >= 11 && i <= 16) {
				Assertions.assertEquals("", token.getStringValue());
			}
			else if (i == 17) {
				Assertions.assertEquals("babla2", token.getStringValue());
			}
			else if (i == 18) {
				Assertions.assertEquals("colon:", token.getStringValue());
			}
			else if (i == 19) {
				Assertions.assertEquals("space", token.getStringValue());
			}
			else if (i == 21) {
				Assertions.assertEquals("comment//not_a_comment", token.getStringValue());
			}
			else if (i == 22) {
				Assertions.assertEquals("comment/*not_a_comment*/", token.getStringValue());
			}
		});
	}

}
