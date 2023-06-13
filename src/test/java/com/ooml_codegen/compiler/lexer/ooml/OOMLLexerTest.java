package com.ooml_codegen.compiler.lexer.ooml;

import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class OOMLLexerTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/lexer/ooml/files/";

	protected String filePath;
	protected OOMLLexer lexer;
	protected AtomicInteger index;

	protected OOMLLexerTest(String fileName) {
		this.filePath = pathPrefix + fileName;
	}

	@BeforeEach
	public void setup() {
		this.lexer = new OOMLLexer(filePath);
		this.index = new AtomicInteger(1);
	}

}
