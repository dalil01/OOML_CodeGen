package com.ooml_codegen.models.tree.nodes.leafs;

public class LAccessModifierPrivate extends LAccessModifier {

	public LAccessModifierPrivate() {
		super("-");
	}

	@Override
	public String getValueForJava() {
		return "private";
	}

}
