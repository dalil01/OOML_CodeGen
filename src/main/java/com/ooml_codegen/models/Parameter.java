package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

public class Parameter {

	private final String name;
	private final Type type;

	public Parameter(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

	public String toStringJava() {
		StringBuilder s = new StringBuilder();
		s.append(this.type.toString()).append(" ").append(this.name);
		return s.toString();
	}

}
