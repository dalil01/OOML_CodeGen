package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.models.nodes.NParameter;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.validator.ooml.OOMLValidator;

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
	}

	@Override
	protected void addComment(LComment comment) {
		this.nParameter.addChild(comment);
	}

}
