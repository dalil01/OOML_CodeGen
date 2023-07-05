package com.ooml_codegen.models.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeAccessModifierTest {

	@Test
	public void getValueForOOMLTest() {
		Assertions.assertEquals("", AttributeAccessModifier.DEFAULT.getValueForOOML());
		Assertions.assertEquals("+", AttributeAccessModifier.PUBLIC.getValueForOOML());
		Assertions.assertEquals("-", AttributeAccessModifier.PRIVATE.getValueForOOML());
		Assertions.assertEquals("#", AttributeAccessModifier.PROTECTED.getValueForOOML());
	}

	@Test
	public void getValueForJavaTest() {
		Assertions.assertEquals("", AttributeAccessModifier.DEFAULT.getValueForJava());
		Assertions.assertEquals("public", AttributeAccessModifier.PUBLIC.getValueForJava());
		Assertions.assertEquals("private", AttributeAccessModifier.PRIVATE.getValueForJava());
		Assertions.assertEquals("protected", AttributeAccessModifier.PROTECTED.getValueForJava());
	}

}