package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.nodes.NConstructor;
import com.ooml.codegen.models.nodes.leafs.LAccessModifierConstructor;
import com.ooml.codegen.models.nodes.leafs.LName;

import java.util.List;

public class ConstructorModelizer extends Modelizer {

	public ConstructorModelizer() {
		super(new NConstructor());
	}

	public void addAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierConstructor(value));
		});
	}

	public void addName(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LName(value));
		});
	}

}
