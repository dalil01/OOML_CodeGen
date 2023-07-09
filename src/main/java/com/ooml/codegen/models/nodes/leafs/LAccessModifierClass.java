package com.ooml.codegen.models.nodes.leafs;

import com.ooml.codegen.utils.ULogger;

public class LAccessModifierClass extends LAccessModifier {

	private final LAccessModifier modifier;

	public LAccessModifierClass(String sign) {
		super(sign);
		this.modifier = this.findModifierFromOOMLSign(sign);
	}

	@Override
	public String getValueForJava() {
		return this.modifier.getValueForJava();
	}

	@Override
	public LAccessModifier findModifierFromOOMLSign(String sign) {
		LAccessModifier accessModifier = super.findModifierFromOOMLSign(sign);

		if (accessModifier instanceof Protected) {
			// TODO error
			ULogger.error("invalid accessModifier");
			throw new IllegalStateException("Unexpected value: " + sign);
		}

		return accessModifier;
	}

}
