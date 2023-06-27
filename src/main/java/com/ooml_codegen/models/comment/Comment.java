package com.ooml_codegen.models.comment;

public abstract class Comment {

	private final String value;

	protected Comment(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
