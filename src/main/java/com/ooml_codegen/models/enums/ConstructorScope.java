package com.ooml_codegen.models.enums;

public enum ConstructorScope {

	PUBLIC('+'),
	PRIVATE('-'),
	PROTECTED('#');

	private final char value;

	ConstructorScope(char value) {
		this.value = value;
	}

	public char getValue() {
		return this.value;
	}

}
