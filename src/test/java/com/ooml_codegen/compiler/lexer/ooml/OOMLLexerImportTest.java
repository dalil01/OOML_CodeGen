package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class OOMLLexerImportTest extends OOMLLexerTest {

	public OOMLLexerImportTest() {
		super("import.ooml");
	}

	@Test
	public void importsTest() throws FileNotFoundException {
		this.lexer.tokenize().forEach(token -> {
			int currentLine = this.index.getAndIncrement();

			System.out.println(token.toString());


			if (List.of(1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 22, 23).contains(currentLine)) {
				Assertions.assertEquals(TokenType.IMPORT, token.type());
			}



			if (currentLine == 1) {
				Assertions.assertEquals("main.ooml", token.value());
			}
			else if (currentLine == 2) {
				Assertions.assertEquals("../models/models.ooml", token.value());
			}
			else if (currentLine == 3) {
				Assertions.assertEquals("../../controller/controllers.ooml/", token.value());
			}
			else if (currentLine == 5) {
				Assertions.assertEquals("All", token.value());
			}
			else if (currentLine == 6) {
				Assertions.assertEquals("Services/", token.value());
			}
			else if (currentLine == 7) {
				Assertions.assertEquals("config.ooml", token.value());
			}
			else if (currentLine == 8) {
				Assertions.assertEquals("/", token.value());
			}
			else if (currentLine == 9) {
				Assertions.assertEquals("dir.", token.value());
			}
			else if (currentLine >= 11 && currentLine <= 16) {
				Assertions.assertEquals("", token.value());
			}
			else if (currentLine == 17) {
				Assertions.assertEquals("babla2", token.value());
			}
			else if (currentLine == 18) {
				Assertions.assertEquals("colon", token.value());
			}
			else if (currentLine == 20) {
				Assertions.assertEquals("space", token.value());
			}
			else if (currentLine == 22) {
				Assertions.assertEquals("comment//not_a_comment", token.value());
			}
			else if (currentLine == 23) {
				Assertions.assertEquals("comment/*not_a_comment*/", token.value());
			}


		});
	}

}
