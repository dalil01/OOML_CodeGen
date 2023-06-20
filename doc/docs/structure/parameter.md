---
sidebar_position: 5
---

# Parameter

```java
package com.ooml_codegen.models;

public class Parameter {

	private final String name;
	private final Type type;

	public Parameter(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public Type getType() {
		return this.type;
	}

}

```