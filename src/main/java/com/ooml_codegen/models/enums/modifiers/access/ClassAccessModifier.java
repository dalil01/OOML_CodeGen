package com.ooml_codegen.models.enums.modifiers.access;

public enum ClassAccessModifier {

	PUBLIC('+'),
	PRIVATE('-');

	private final char value;

	ClassAccessModifier(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
