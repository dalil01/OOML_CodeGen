---
sidebar_position: 7
---

# Method

```java
package com.ooml_codegen.models;

import com.ooml_codegen.models.modifiers.access.MethodAccessModifier;

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

}
·
```