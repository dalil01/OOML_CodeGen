package com.ooml.codegen.validator.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.validator.Validator;

public abstract class OOMLValidator extends Validator {

	public OOMLValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

}
