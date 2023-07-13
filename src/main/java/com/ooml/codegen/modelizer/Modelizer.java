package com.ooml.codegen.modelizer;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.LCommentMultiLine;
import com.ooml.codegen.models.nodes.leafs.LCommentSingleLine;

import java.util.List;
import java.util.function.BiConsumer;

public abstract class Modelizer {

	private final Node model;

	protected Modelizer(Node model) {
		this.model = model;
	}

	public Node getModel() throws Exception {
		this.model.check();
		return this.model;
	}

	protected void foreachAndConsumeComments(List<Token> tokenList, BiConsumer<TokenType, String> action) {
		for (Token token : tokenList) {
			if (token.isComment()) {
				this.addComment(token);
				continue;
			}

			action.accept(token.getType(), token.getValue());
		}
	}

	protected void addChild(Node node) {
		this.model.addChild(node);
	}

	protected void addComment(Token token) {
		if (token.getType() == Token.TokenType.SINGLE_LINE_COMMENT) {
			this.model.addChild(new LCommentSingleLine(token.getValue()));
		} else {
			this.model.addChild(new LCommentMultiLine(token.getValue()));
		}
	}

}
