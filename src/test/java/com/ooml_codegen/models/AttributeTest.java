package com.ooml_codegen.models;

import com.ooml_codegen.models.modifiers.access.AttributeAccessModifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeTest {

	@Test
	public void constructorTest() {
		String expectedName = "name";
		Type expectedType = new Type("string");

		Attribute attribute = new Attribute(expectedName, expectedType);
		attribute.setAccessModifier(AttributeAccessModifier.PROTECTED);

		Assertions.assertEquals(expectedName, attribute.getName());
		Assertions.assertEquals(expectedType, attribute.getType());
	}

	@Test
	public void getNameTest() {
		String expectedName = "id";
		Type type = new Type("int");
		Attribute attribute = new Attribute(expectedName, type);
		attribute.setAccessModifier(AttributeAccessModifier.PRIVATE);
		Assertions.assertEquals(expectedName, attribute.getName());
	}

	@Test
	public void getAccessModifierTest() {
		String expectedName = "id";
		Type type = new Type("int");
		Attribute attribute = new Attribute(expectedName, type);
		attribute.setAccessModifier(AttributeAccessModifier.PRIVATE);
		Assertions.assertEquals(AttributeAccessModifier.PRIVATE, attribute.getAccessModifier());
	}

	@Test
	public void getTypeTest() {
		String expectedName = "user";
		Type type = new Type("User");
		Attribute attribute = new Attribute(expectedName, type);
		attribute.setAccessModifier(AttributeAccessModifier.PUBLIC);
		Assertions.assertEquals(type, attribute.getType());
	}

}
