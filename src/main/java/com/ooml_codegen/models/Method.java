package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.MethodScope;

import java.util.ArrayList;
import java.util.List;

public class Method {

	private final String name;
	private final MethodScope scope;
	private final List<Parameter> parameters = new ArrayList<>();
	private final Type returnType;

	public Method(String name, MethodScope scope, Type returnType) {
		this.name = name;
		this.scope = scope;
		this.returnType = returnType;
	}

	public String getName() {
		return this.name;
	}

	public MethodScope getScope() {
		return this.scope;
	}

	public Type getReturnType() {
		return this.returnType;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

}
