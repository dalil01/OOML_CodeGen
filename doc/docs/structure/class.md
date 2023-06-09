---
sidebar_position: 2
---

# Class

```java
package com.ooml_codegen.models;

import com.ooml_codegen.models.modifiers.access.ClassAccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Class {

	private final String name;
	private final Package cPackage;
	private final ClassAccessModifier accessModifier;
	private final List<Attribute> attributes = new ArrayList<>();
	private final List<Constructor> constructors = new ArrayList<>();
	private final List<Method> methods = new ArrayList<>();

	public Class(String name, Package cPackage, ClassAccessModifier accessModifier) {
		this.name = name;
		this.cPackage = cPackage;
		this.accessModifier = accessModifier;
	}

	public String getName() {
		return this.name;
	}

	public Package getPackage() {
		return this.cPackage;
	}

	public ClassAccessModifier getAccessModifier() {
		return this.accessModifier;
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
```