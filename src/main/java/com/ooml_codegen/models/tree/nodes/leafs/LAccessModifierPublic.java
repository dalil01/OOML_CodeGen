package com.ooml_codegen.models.tree.nodes.leafs;

public class LAccessModifierPublic extends LAccessModifier {

	public LAccessModifierPublic() {
		super("+");
	}

	@Override
	public String getValueForJava() {
		return "public";
	}

}
