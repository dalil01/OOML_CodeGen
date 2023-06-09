package com.ooml_codegen.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterTest {

	private final String expectedName = "id";
	private final Type expectedType = new Type("string");

	private Parameter parameter;

	@BeforeEach
	public void setup() {
		this.parameter = new Parameter(this.expectedName, this.expectedType);
	}

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.parameter.getName());
		Assertions.assertEquals(this.expectedType, this.parameter.getType());
	}

	@Test
	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.parameter.getName());
	}

	@Test
	public void getTypeTest() {
		Assertions.assertEquals(this.expectedType, this.parameter.getType());
	}

}
