package com.ooml_codegen.models.enums.modifiers.access;

public enum MethodAccessModifier {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	MethodAccessModifier(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
