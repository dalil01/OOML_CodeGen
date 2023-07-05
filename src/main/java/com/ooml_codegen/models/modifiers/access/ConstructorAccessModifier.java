package com.ooml_codegen.models.modifiers.access;

public enum ConstructorAccessModifier {

	DEFAULT,
	PUBLIC,
	PRIVATE,
	PROTECTED;

	public String getValueForOOML() {
		return switch (this) {
			case DEFAULT -> "";
			case PUBLIC -> "+";
			case PRIVATE -> "-";
			case PROTECTED -> "#";
		};
	}

	public String getValueForJava() {
		return switch (this) {
			case DEFAULT -> "";
			case PUBLIC -> "public";
			case PRIVATE -> "private";
			case PROTECTED -> "protected";
		};
	}

}
