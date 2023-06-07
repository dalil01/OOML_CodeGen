package com.ooml_codegen.models.enums;

public enum MethodScope {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	MethodScope(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
