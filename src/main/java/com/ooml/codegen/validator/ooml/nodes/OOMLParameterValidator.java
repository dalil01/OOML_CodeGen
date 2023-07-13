package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.nodes.NParameter;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.List;

public class OOMLParameterValidator extends OOMLValidator {

	private final NParameter nParameter = new NParameter();

	public OOMLParameterValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NParameter getValidatedNode() {
		return this.nParameter;
	}

	@Override
	public void validate() throws Exception {
		/*
		Token nextToken = this.nextToken();

		System.out.println(nextToken);

		 */

	}

	public void addChildren(List<Token> tokenList) throws Exception {

	}

}
