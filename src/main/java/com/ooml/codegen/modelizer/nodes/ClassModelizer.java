package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.nodes.NClass;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.List;

public class ClassModelizer extends Modelizer {

	public ClassModelizer() {
		super(new NClass());
	}

	public void addPackage(List<Token> tokenList) throws Exception {
		PackageModelizer packageModelizer = new PackageModelizer();
		packageModelizer.addPackage(tokenList);
		this.addChild(packageModelizer.getModel());
	}

	public void addAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierClass(value));
		});
	}

	public void addNonAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LNonAccessModifier(value));
		});
	}

	public void addDeclaration(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LDeclaration(value));
		});
	}

	public void addName(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LName(value));
		});
	}

	public void addClassInheritance(List<Token> tokenList) throws Exception {
		InheritanceModelizer modelizer = new InheritanceModelizer.Class();
		modelizer.addInheritance(tokenList);
		this.addChild(modelizer.getModel());
	}

	public void addInterfaceInheritance(List<Token> tokenList) throws Exception {
		InheritanceModelizer modelizer = new InheritanceModelizer.Interface();
		modelizer.addInheritance(tokenList);
		this.addChild(modelizer.getModel());
	}



}
