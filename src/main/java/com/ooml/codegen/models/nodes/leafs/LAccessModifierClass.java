package com.ooml.codegen.models.nodes.leafs;

import com.ooml.codegen.utils.ULogger;

public class LAccessModifierClass extends LAccessModifier {

	public LAccessModifierClass(String sign) {
		super(sign);
	}

	@Override
	public LAccessModifier findModifierFromSignValue() {
		LAccessModifier accessModifier = super.findModifierFromSignValue();

		if (accessModifier instanceof Protected) {
			// TODO error
			ULogger.error("invalid accessModifier");
			throw new IllegalStateException("Unexpected value: " + this.getValue());
		}

		return accessModifier;
	}

}
