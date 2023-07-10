package com.ooml.codegen.models.nodes.leafs;

public class LAccessModifierMethod extends LAccessModifier {

	private final LAccessModifier modifier;

	public LAccessModifierMethod(String sign) {
		super(sign);
		this.modifier = this.findModifierFromSignValue();
	}

	@Override
	public String getValueForJava() {
		return this.modifier.getValueForJava();
	}

}
