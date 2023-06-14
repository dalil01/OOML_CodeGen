package com.ooml_codegen.compiler.lexer;

import com.ooml_codegen.compiler.lexer.ooml.OOMLLexer;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LexerTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/lexer/ooml/files/";

	protected String filePath;
	protected Lexer lexer;
	protected AtomicInteger index;

	protected LexerTest(String fileName) {
		this.filePath = pathPrefix + fileName;
	}

	@BeforeEach
	public void setup() throws FileNotFoundException {
		this.lexer = new OOMLLexer(filePath);
		this.index = new AtomicInteger(1);
	}

	/**
	 * We need this to ensure that the tests work on all operating systems.
	 * @link : https://stackoverflow.com/questions/1552749/difference-between-cr-lf-lf-and-cr-line-break-types
	 */
	public static String normalizedString(String s) {
		return s.replaceAll("\r\n", "\n");
	}

}
