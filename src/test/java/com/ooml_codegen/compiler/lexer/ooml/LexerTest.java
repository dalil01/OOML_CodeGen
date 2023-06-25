package com.ooml_codegen.compiler.lexer.ooml;

import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class LexerTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/lexer/ooml/all/files/";

	protected String filePath;
	protected OOMLLexer lexer;

	protected LexerTest(String fileName) {
		this.filePath = pathPrefix + fileName;
	}

	@BeforeEach
	public void setup() throws FileNotFoundException {
		this.lexer = new OOMLLexer(new File(this.filePath));
	}

	/**
	 * We need this to ensure that the tests work on all operating systems.
	 * @link : https://stackoverflow.com/questions/1552749/difference-between-cr-lf-lf-and-cr-line-break-types
	 */
	public static String normalizedString(String s) {
		return s.replaceAll("\r\n", "\n");
	}

}
