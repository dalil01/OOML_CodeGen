package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;

import java.util.List;

public class PackageValidator extends Validator {


	protected PackageValidator(LexerManager lexerManager, List<Token> unConsumedTokenList) {
		super(lexerManager, unConsumedTokenList);
	}

	@Override
	public void validate() {
	}

}
