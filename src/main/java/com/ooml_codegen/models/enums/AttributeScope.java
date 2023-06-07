package com.ooml_codegen.models.enums;

public enum AttributeScope {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	AttributeScope(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
