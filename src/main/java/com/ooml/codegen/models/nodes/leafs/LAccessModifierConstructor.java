package com.ooml.codegen.models.nodes.leafs;

public class LAccessModifierConstructor extends LAccessModifier {

	private final LAccessModifier modifier;

	public LAccessModifierConstructor(String sign) {
		super(sign);
		this.modifier = this.findModifierFromOOMLSign(sign);
	}

	@Override
	public String getValueForJava() {
		return this.modifier.getValueForJava();
	}

}
