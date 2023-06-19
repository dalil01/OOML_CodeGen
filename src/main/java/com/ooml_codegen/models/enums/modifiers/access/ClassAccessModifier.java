package com.ooml_codegen.models.enums.modifiers.access;

public enum ClassAccessModifier {

	PUBLIC,
	PRIVATE;

	public char getValueForOOML() {
		return switch (this) {
			case PUBLIC -> '+';
			case PRIVATE -> '-';
		};
	}

	public String getValueForJava() {
		return switch (this) {
			case PUBLIC -> "public";
			case PRIVATE -> "private";
		};
	}

}
