package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.nodes.NInterface;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.List;

public class OOMLInterfaceValidator extends OOMLValidator {

	private final NInterface nInterface = new NInterface();

	public OOMLInterfaceValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NInterface getValidatedNode() {
		return this.nInterface;
	}

	@Override
	public void validate() throws Exception {
	}

	public void addChildren(List<Token> tokenList) throws Exception {

	}

}
