package com.ooml.codegen.lexer.ooml;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.utils.UFiles;

import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;

public class OOMLLexerManager extends LexerManager {

	public OOMLLexerManager(File file) throws FileNotFoundException {
		super(new OOMLLexer(file));
	}

	protected void manageImport(Token importToken) throws FileNotFoundException {
		// Stack should never be empty when entering this function (how else would we have gotten an import token?)
		assert this.stack.size() != 0;
		assert this.stack.peek() != null;

		if (importToken.getValue().isEmpty()) {
			// TODO
			ULogger.warn("Empty import token at " + importToken.getLocation());
			return;
		}

		List<File> files = UFiles.findOOMLFilesPath(importToken.getValue(), this.stack.peek().getFile());

		if (files.isEmpty()) {
			// TODO
			ULogger.error("Couldn't import " + importToken.getValue() + " at " + importToken.getLocation());
			throw new FileNotFoundException("Couldn't import " + importToken.getLocation());
		}

		for (File file : files) {
			this.stack.push(new OOMLLexer(file));
		}
	}

}
