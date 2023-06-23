package com.ooml_codegen.compiler.validator.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.validator.ValidationType;
import com.ooml_codegen.compiler.validator.Validator;

import java.util.List;
import java.util.Optional;

public class OOMLValidator extends Validator {

	@Override
	public boolean validate(ValidationType type, Optional<String> value) {
		switch (type) {
			case PACKAGE -> {
				return this.validatePackage(value);
			}
		}

		return false;
	}

	@Override
	public boolean validate(ValidationType type, List<Token> list) {
		return false;
	}

	private boolean validatePackage(Optional<String> packageName) {
		// TODO
		return false;
	}

}
