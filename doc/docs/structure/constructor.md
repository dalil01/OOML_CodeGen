---
sidebar_position: 4
---

# Constructor

```java
package com.ooml_codegen.models;

import com.ooml_codegen.models.modifiers.access.ConstructorAccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Constructor {

	private final String name;
	private final ConstructorAccessModifier accessModifier;
	private final List<Parameter> parameters = new ArrayList<>();

	public Constructor(String name, ConstructorAccessModifier accessModifier) {
		this.name = name;
		this.accessModifier = accessModifier;
	}

	public String getName() {
		return this.name;
	}

	public ConstructorAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public boolean addParameter(Parameter parameter) {
		return this.parameters.add(parameter);
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

}

```
