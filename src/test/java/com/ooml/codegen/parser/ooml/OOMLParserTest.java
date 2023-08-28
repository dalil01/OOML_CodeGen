package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.generator.Generator;
import com.ooml.codegen.generator.java.JavaGenerator;
import com.ooml.codegen.lexer.ooml.OOMLLexerManager;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.NClass;
import com.ooml.codegen.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;

public class OOMLParserTest {

	private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml/codegen" +
			"/parser/ooml/files/";

	@Test
	public void parseTest() throws Exception {
		OOMLLexerManager lexer = new OOMLLexerManager(new File(pathPrefix + "main.ooml"));
		Parser parser = new OOMLParser(lexer);

		Node node = parser.parse();
		node.printTree();

		if (node instanceof NClass) {
			Generator generator = new JavaGenerator((NClass) node);
			System.out.println("-------------------------------------------------------------------------");
			generator.generate();
			generator.printGenerated();
		}
	}

}
