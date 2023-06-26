package com.ooml_codegen.compiler.generator.enums;

public enum GenerationContext {

	PACKAGE("package"),
	IMPORTS("imports"),
	CLASS_ACCESS_MODIFIER("classAccessModifier"),
	CLASS_NAME("className"),
	INTERFACE_ACCESS_MODIFIER("interfaceAccessModifier"),
	INTERFACE_NAME("interfaceName"),
	METHODS("methods"),
	;


	private final String value;

	GenerationContext(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
