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

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public List<Constructor> getConstructors() {
		return this.constructors;
	}

	public List<Method> getMethods() {
		return this.methods;
	}

}
