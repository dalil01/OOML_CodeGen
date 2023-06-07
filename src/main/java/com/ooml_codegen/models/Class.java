package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.ClassScope;

import java.util.ArrayList;
import java.util.List;

public class Class {

	private final String name;
	private final Package cPackage;
	private final ClassScope scope;
	private final List<Attribute> attributes = new ArrayList<>();
	private final List<Constructor> constructors = new ArrayList<>();
	private final List<Method> methods = new ArrayList<>();

	public Class(String name, Package cPackage, ClassScope scope) {
		this.name = name;
		this.cPackage = cPackage;
		this.scope = scope;
	}

	public String getName() {
		return this.name;
	}

	public Package getPackage() {
		return this.cPackage;
	}

	public ClassScope getScope() {
		return this.scope;
	}

	public boolean addAttribute(Attribute attribute) {
		return this.attributes.add(attribute);
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public boolean addConstructor(Constructor constructor) {
		return this.constructors.add(constructor);
	}

	public List<Constructor> getConstructors() {
		return this.constructors;
	}

	public boolean addMethod(Method method) {
		return this.methods.add(method);
	}

	public List<Method> getMethods() {
		return this.methods;
	}

}
