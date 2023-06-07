package com.ooml_codegen.models;

import com.ooml_codegen.models.Attribute;
import com.ooml_codegen.models.Type;
import com.ooml_codegen.models.enums.AttributeAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeTest {

	@Test
	public void constructorTest() {
		String expectedName = "name";
		Type expectedType = new Type("string");

		Attribute attribute = new Attribute(expectedName, AttributeAccessModifier.PROTECTED, expectedType);

		Assertions.assertEquals(expectedName, attribute.getName());
		Assertions.assertEquals(expectedType, attribute.getType());
	}

	@Test
	public void getNameTest() {
		String expectedName = "id";
		Type type = new Type("int");
		Attribute attribute = new Attribute(expectedName, AttributeAccessModifier.PRIVATE, type);
		Assertions.assertEquals(expectedName, attribute.getName());
	}

	@Test
	public void getAccessModifierTest() {
		String expectedName = "id";
		Type type = new Type("int");
		Attribute attribute = new Attribute(expectedName, AttributeAccessModifier.PRIVATE, type);
		Assertions.assertEquals(AttributeAccessModifier.PRIVATE, attribute.getAccessModifier());
	}

	@Test
	public void getTypeTest() {
		String expectedName = "user";
		Type type = new Type("User");
		Attribute attribute = new Attribute(expectedName, AttributeAccessModifier.PUBLIC, type);
		Assertions.assertEquals(type, attribute.getType());
	}

}
