package com.ooml_codegen.lexer.ooml;

public enum OOMLKeyword {

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
