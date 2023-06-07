package com.ooml_codegen.models.enums;

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
