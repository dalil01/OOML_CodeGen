package com.ooml_codegen.models;

public class Attribute {

	private final String name;
	private final Type type;

	public Attribute(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

}
