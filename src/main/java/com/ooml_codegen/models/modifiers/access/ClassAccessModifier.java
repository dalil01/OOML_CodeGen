package com.ooml_codegen.models.modifiers.access;

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

	public static ClassAccessModifier getModifierFromOOMLSign(String sign) {
		return switch (sign) {
			case "" -> ClassAccessModifier.DEFAULT;
			case "+" -> ClassAccessModifier.PUBLIC;
			case "-" -> ClassAccessModifier.PRIVATE;
			default -> null;
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
