package com.ooml_codegen.models.enums.modifiers.access;

public enum ClassAccessModifier {

	DEFAULT,
	PUBLIC,
	PRIVATE;

	public String getValueForOOML() {
		return switch (this) {
			case DEFAULT -> "";
			case PUBLIC -> "+";
			case PRIVATE -> "-";
		};
	}

	public String getValueForJava() {
		return switch (this) {
			case DEFAULT -> "";
			case PUBLIC -> "public";
			case PRIVATE -> "private";
		};
	}

}
