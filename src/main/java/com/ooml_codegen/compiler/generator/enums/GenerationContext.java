package com.ooml_codegen.compiler.generator.enums;

public enum GenerationContext {

	PACKAGE("package"),
	IMPORTS("imports"),
	CLASS_ACCESS_MODIFIER("classAccessModifier"),
	CLASS_NAME("className"),
	ENUMS_ACCESS_MODIFIER("enumAccessModifier"),
	ENUMS_NAME("enumName"),
	;

	private final String value;

	GenerationContext(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
