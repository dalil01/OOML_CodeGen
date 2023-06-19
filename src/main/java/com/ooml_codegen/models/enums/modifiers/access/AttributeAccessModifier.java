package com.ooml_codegen.models.enums.modifiers.access;

public enum AttributeAccessModifier {

	PUBLIC,
	PRIVATE,
	PROTECTED;

	public char getValueForOOML() {
		return switch (this) {
			case PUBLIC -> '+';
			case PRIVATE -> '-';
			case PROTECTED -> '#';
		};
	}

	public String getValueForJava() {
		return switch (this) {
			case PUBLIC -> "public";
			case PRIVATE -> "private";
			case PROTECTED -> "protected";
		};
	}

}
