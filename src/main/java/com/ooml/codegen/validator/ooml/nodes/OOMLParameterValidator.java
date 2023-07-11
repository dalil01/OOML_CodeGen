package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.nodes.NParameter;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.utils.ULogger;
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
		Token nextToken = this.nextToken();

		while (nextToken.getType() != Token.TokenType.CLOSING_PARENTHESIS) {
			if (nextToken.getType() != Token.TokenType.EOF) {
				// TODO
				ULogger.error("Missing token ");
				throw new Exception();
			}



			nextToken = this.nextToken();
		}

		this.insertToken(nextToken);
	}

	@Override
	protected void addComment(LComment comment) {
		this.nParameter.addChild(comment);
	}

}
