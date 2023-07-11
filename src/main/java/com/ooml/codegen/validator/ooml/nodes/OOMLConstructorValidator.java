package com.ooml.codegen.validator.ooml.nodes;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.nodes.NConstructor;
import com.ooml.codegen.models.nodes.leafs.LAccessModifierConstructor;
import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.models.nodes.leafs.LName;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.validator.ooml.OOMLValidator;

public class OOMLConstructorValidator extends OOMLValidator {

	private final NConstructor nConstructor = new NConstructor();

	public OOMLConstructorValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public NConstructor getValidatedNode() {
		return this.nConstructor;
	}

	@Override
	public void validate() throws Exception {
		this.validateAccessModifier();
		this.validateName();
		this.validateParameters();

		Token nextToken = this.nextToken();
		if (nextToken.getType() != Token.TokenType.SEMI_COLON) {
			this.insertToken(nextToken);
		}
	}

	private void validateAccessModifier() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == Token.TokenType.SIGN || nextToken.getType() == Token.TokenType.ACCESS_MODIFIER_BLOCK) {
			this.nConstructor.addChild(new LAccessModifierConstructor(nextToken.getValue()));
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateName() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() != TokenType.OPENING_PARENTHESIS) {
			this.nConstructor.addChild(new LName(nextToken.getValue()));
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateParameters() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() != TokenType.OPENING_PARENTHESIS) {
			// TODO
			ULogger.error("unexpected token ");
			throw new Exception();
		}
	}

	@Override
	protected void addComment(LComment comment) {
		this.nConstructor.addChild(comment);
	}

}
