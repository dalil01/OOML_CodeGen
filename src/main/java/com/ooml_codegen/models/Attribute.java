package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;

public class Attribute {

	private final String name;
	private final AttributeAccessModifier accessModifier;
	private final Type type;
	private String value = "";

	public Attribute(String name, AttributeAccessModifier accessModifier, Type type, String value) {
		this.name = name;
		this.accessModifier = accessModifier;
		this.type = type;
		this.value = value;
	}

	public Attribute(String name, AttributeAccessModifier accessModifier, Type type) {
		this.name = name;
		this.accessModifier = accessModifier;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public AttributeAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public Type getType() {
		return this.type;
	}

	public String getValue() { return this.value; }

	public String toStringJava() {
		StringBuilder s = new StringBuilder();
		if (this.accessModifier != AttributeAccessModifier.DEFAULT) {
			s.append(this.accessModifier.getValueForJava()).append(" ");
		}

		s.append(this.type.getName()).append(" ").append(this.name);

		if (!this.value.isEmpty()) {
			s.append(" = ").append(this.value);
		}

		return s.toString();
	}

}
