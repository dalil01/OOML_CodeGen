package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class OOMLParserTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/parser/ooml/files/";

	@Test
	public void parseTest() throws Exception {
		OOMLLexerManager lexer = new OOMLLexerManager(new File(pathPrefix + "main.ooml"));
		Parser parser = new OOMLParser(lexer);

		parser.parse();
	}

}
