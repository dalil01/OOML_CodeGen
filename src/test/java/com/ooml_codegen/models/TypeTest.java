package com.ooml_codegen.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TypeTest {

	private final String expectedName = "string";

	private Type type;

	@BeforeEach
	public void setup() {
		this.type = new Type(this.expectedName);
	}

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.type.getName());
	}

	@Test
	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.type.getName());
	}

}
