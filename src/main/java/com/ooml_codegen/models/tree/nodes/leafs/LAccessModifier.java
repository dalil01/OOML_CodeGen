package com.ooml_codegen.models.tree.nodes.leafs;

import com.ooml_codegen.models.tree.Leaf;

public abstract class LAccessModifier extends Leaf {

	public LAccessModifier(String value) {
		super(value);
	}

	public abstract String getValueForJava();

}
