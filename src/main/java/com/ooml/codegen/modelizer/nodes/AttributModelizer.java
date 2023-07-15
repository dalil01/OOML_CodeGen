package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.nodes.NAttribut;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.List;

public class AttributModelizer extends Modelizer {

	public AttributModelizer() {
		super(new NAttribut());
	}

	public void addAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierAttribut(value));
		});
	}

	public void addNonAccessModifiers(List<Token> tokenList) {
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
