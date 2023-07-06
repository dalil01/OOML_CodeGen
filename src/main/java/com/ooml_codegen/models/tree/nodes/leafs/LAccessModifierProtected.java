package com.ooml_codegen.models.tree.nodes.leafs;

public class LAccessModifierProtected extends LAccessModifier {

	public LAccessModifierProtected() {
		super("#");
	}

	@Override
	public String getValueForJava() {
		return "protected";
	}

}
