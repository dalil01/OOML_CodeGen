package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.nodes.NMethod;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.validator.ooml.OOMLValidator;

import java.util.List;

public class OOMLMethodValidator extends OOMLValidator {

	private final NMethod nMethod = new NMethod();

	public OOMLMethodValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NMethod getValidatedNode() {
		return this.nMethod;
	}

	@Override
	public void validate() throws Exception {

		/*this.validateAccessModifier();

		Token nextToken = this.nextToken();
		if (nextToken.getType() != Token.TokenType.SEMI_COLON) {
		//	this.insertToken(nextToken);
		}

		 */
	}

	private void validateAccessModifier() throws Exception {

	}

	public void addChildren(List<Token> tokenList) throws Exception {

	}

}
