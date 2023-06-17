package com.ooml_codegen.compiler.validator;

import com.ooml_codegen.compiler.lexer.Token;

import java.util.List;

public abstract class Validator {

	public Validator() {
	}

	public abstract boolean validate(ValidationType type, String value);

	public abstract boolean validate(ValidationType type, List<Token> list);

}
