package com.ooml_codegen.models.enums.modifiers.access;

public enum ConstructorAccessModifier {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	ConstructorAccessModifier(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
