package com.ooml_codegen.models.tree.nodes.leafs;

public class LAccessModifierDefault extends LAccessModifier {

	public LAccessModifierDefault() {
		super("");
	}

	@Override
	public String getValueForJava() {
		return this.getValue();
	}

}
