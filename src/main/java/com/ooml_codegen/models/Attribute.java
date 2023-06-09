package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;

public class Attribute {

	private final String name;
	private final AttributeAccessModifier accessModifier;
	private final Type type;

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

}
