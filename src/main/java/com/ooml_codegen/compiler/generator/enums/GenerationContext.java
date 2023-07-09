package com.ooml_codegen.compiler.generator.enums;

public enum GenerationContext {

	PACKAGE("package"),
	IMPORTS("imports"),
	CLASS_ACCESS_MODIFIER("classAccessModifier"),
	CLASS_NAME("className"),
	METHODS("methods"),
	CONSTRUCTORS("constructors"),
	ATTRIBUTES("attributes"),
	PARAMETERS("params"),

	ENUMS_ACCESS_MODIFIER("enumAccessModifier"),
	ENUMS_NAME("enumName"),
	ENUMERATIONS("enumerations"),
	ENUM_WITH_VALUE("withValue"),
	ENUM_WITHOUT_VALUE("withoutValue"),

	INTERFACE_ACCESS_MODIFIER("interfaceAccessModifier"),
	INTERFACE_NAME("interfaceName"),
	CONSTANTS("constants"),
	;


	private final String value;

	GenerationContext(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
