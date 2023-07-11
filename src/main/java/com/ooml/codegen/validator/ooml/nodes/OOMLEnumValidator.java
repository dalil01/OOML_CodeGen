package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.models.nodes.NEnum;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.validator.ooml.OOMLValidator;

public class OOMLEnumValidator extends OOMLValidator {

	private final NEnum nEnum = new NEnum();

	public OOMLEnumValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NEnum getValidatedNode() {
		return this.nEnum;
	}

	@Override
	public void validate() throws Exception {
	}

	@Override
	protected void addComment(LComment comment) {
		this.nEnum.addChild(comment);
	}

}
