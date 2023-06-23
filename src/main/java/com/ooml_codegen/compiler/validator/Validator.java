package com.ooml_codegen.compiler.validator;

import com.ooml_codegen.compiler.lexer.Token;

import java.util.List;
import java.util.Optional;

public abstract class Validator {

	public Validator() {
	}

	public abstract boolean validate(ValidationType type, Optional<String> value);

	public abstract boolean validate(ValidationType type, List<Token> list);

}
