package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.parser.Parser;

import java.util.stream.Stream;

public class OOMLAttributeParser extends Parser {

	public OOMLAttributeParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Stream<ICodeGenNode> parse() throws Exception {
		return null;
	}

}
