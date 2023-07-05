package com.ooml_codegen.models.enums.modifiers.access;

public enum AttributeAccessModifier {

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

	public static AttributeAccessModifier getModifierFromOOMLSign(String sign) {
		return switch (sign) {
			case "" -> AttributeAccessModifier.DEFAULT;
			case "+", "+:" -> AttributeAccessModifier.PUBLIC;
			case "-", "-:" -> AttributeAccessModifier.PRIVATE;
			case "#", "#:" -> AttributeAccessModifier.PROTECTED;
			default -> null;
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
