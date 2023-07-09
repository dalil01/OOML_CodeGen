package com.ooml.codegen.models.nodes.leafs;

public class LAccessModifierAttribut extends LAccessModifier {

	private final LAccessModifier modifier;

	public LAccessModifierAttribut(String sign) {
		super(sign);
		this.modifier = this.findModifierFromOOMLSign(sign);
	}

	@Override
	public String getValueForJava() {
		return this.modifier.getValueForJava();
	}

}
