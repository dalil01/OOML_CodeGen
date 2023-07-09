package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Method {

	private final String name;
	private final MethodAccessModifier accessModifier;
	private final Type returnType;
	private final List<Parameter> parameters = new ArrayList<>();

	public Method(String name, MethodAccessModifier accessModifier, Type returnType) {
		this.name = name;
		this.accessModifier = accessModifier;
		this.returnType = returnType;
	}

	public String getName() {
		return this.name;
	}

	public MethodAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public Type getReturnType() {
		return this.returnType;
	}

	public boolean addParameter(Parameter parameter) {
		return this.parameters.add(parameter);
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

	public String toStringJava() {
		StringBuilder s = new StringBuilder();
		if (this.accessModifier != MethodAccessModifier.DEFAULT) {
			s.append(this.accessModifier.getValueForJava()).append(" ");
		}
		s.append(this.returnType.getName()).append(" ").append(this.name).append("(");

		// Iterate over the parameters and generate a comma-separated string
		for (int i = 0; i < parameters.size(); i++) {
			Parameter parameter = parameters.get(i);
			s.append(parameter.toStringJava());
			if (i < parameters.size() - 1) {
				s.append(", ");
			}
		}

		s.append(")");

		return s.toString();
	}


}
