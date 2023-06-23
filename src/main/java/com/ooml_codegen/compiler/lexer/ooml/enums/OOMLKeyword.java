package com.ooml_codegen.compiler.lexer.ooml.enums;

public enum OOMLKeyword {

	PACKAGE("package"),
	CLASS("class"),
	ENUM("enum"),
	INTERFACE("interface")
	;

	private final String value;

	OOMLKeyword(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
