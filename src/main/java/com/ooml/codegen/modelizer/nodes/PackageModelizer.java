package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.nodes.NPackage;
import com.ooml.codegen.models.nodes.leafs.LDeclaration;
import com.ooml.codegen.models.nodes.leafs.LName;

import java.util.List;

public class PackageModelizer extends Modelizer {

	public PackageModelizer() {
		super(new NPackage());
	}

	public void addPackage(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type == Token.TokenType.PACKAGE) {
				this.addChild(new LDeclaration(value));
			} else if (type == Token.TokenType.WORD || type == Token.TokenType.QUOTED_WORD) {
				this.addChild(new LName(value));
			}
		});
	}

}
