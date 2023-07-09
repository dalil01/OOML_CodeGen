package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.ooml.OOMLLexerManager;
import com.ooml.codegen.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;

public class OOMLParserTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml/codegen/parser/ooml/files/";

	@Test
	public void parseTest() throws Exception {
		OOMLLexerManager lexer = new OOMLLexerManager(new File(pathPrefix + "main.ooml"));
		Parser parser = new OOMLParser(lexer);

		parser.parse();
	}

}
