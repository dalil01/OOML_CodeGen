package com.ooml_codegen.models.enums;

public enum ClassScope {

	PUBLIC('+'),
	PRIVATE('-');

	private final char value;

	ClassScope(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
