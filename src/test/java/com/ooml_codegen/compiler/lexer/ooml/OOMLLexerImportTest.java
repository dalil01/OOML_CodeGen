package com.ooml_codegen.compiler.lexer.ooml;

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
		});
	}

}
