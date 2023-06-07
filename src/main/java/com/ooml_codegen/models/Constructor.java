package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.ConstructorScope;

import java.util.ArrayList;
import java.util.List;

public class Constructor {

	private final String name;
	private final ConstructorScope scope;
	private final List<Parameter> parameters = new ArrayList<>();

	public Constructor(String name, ConstructorScope scope) {
		this.name = name;
		this.scope = scope;
	}

	public String getName() {
		return this.name;
	}

	public ConstructorScope getScope() {
		return this.scope;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

}
