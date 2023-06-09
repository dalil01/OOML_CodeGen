package com.ooml_codegen.models.enums.modifiers.access;

public enum AttributeAccessModifier {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	AttributeAccessModifier(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
