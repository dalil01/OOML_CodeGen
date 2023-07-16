package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.nodes.NConstructor;
import com.ooml.codegen.models.nodes.NParameter;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.List;

public class ParameterModelizer extends Modelizer {

	public ParameterModelizer() {
		super(new NParameter());
	}

	public void addNonAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LNonAccessModifier(value));
		});
	}

	public void addName(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LName(value));
		});
	}

	public void addType(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LType(value));
		});
	}

	public void addDefaultValue(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LValue(value));
		});
	}

}
