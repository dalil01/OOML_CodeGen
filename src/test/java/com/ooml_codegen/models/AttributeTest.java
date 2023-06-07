package com.ooml_codegen.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeTest {

	@Test
	public void constructorTest() {
		String expectedName = "name";
		Type expectedType = new Type("string");

		Attribute attribute = new Attribute(expectedName, expectedType);

		Assertions.assertEquals(expectedName, attribute.getName());
		Assertions.assertEquals(expectedType, attribute.getType());
	}

	@Test
	public void getNameTest() {
		String expectedName = "id";
		Type type = new Type("int");
		Attribute attribute = new Attribute(expectedName, type);
		Assertions.assertEquals(expectedName, attribute.getName());
	}

	@Test
	public void getTypeTest() {
		String expectedName = "user";
		Type type = new Type("User");
		Attribute attribute = new Attribute(expectedName, type);
		Assertions.assertEquals(type, attribute.getType());
	}

}
