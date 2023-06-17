package com.ooml_codegen.compiler.validator.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.validator.ValidationType;
import com.ooml_codegen.compiler.validator.Validator;

import java.util.List;

public class OOMLValidator extends Validator {

	@Override
	public boolean validate(ValidationType type, String value) {
		switch (type) {
			case IMPORT -> {
				return this.validateImport(value);
			}
		}

		return false;
	}

	@Override
	public boolean validate(ValidationType type, List<Token> list) {
		return false;
	}

	private boolean validateImport(String importPath) {
		// TODO
		return false;
	}

}
