package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstructorAccessModifierTest {

	@Test
	public void getValueForOOMLTest() {
		Assertions.assertEquals("", ConstructorAccessModifier.DEFAULT.getValueForOOML());
		Assertions.assertEquals("+", ConstructorAccessModifier.PUBLIC.getValueForOOML());
		Assertions.assertEquals("-", ConstructorAccessModifier.PRIVATE.getValueForOOML());
		Assertions.assertEquals("#", ConstructorAccessModifier.PROTECTED.getValueForOOML());
	}

	@Test
	public void getValueForJavaTest() {
		Assertions.assertEquals("", ConstructorAccessModifier.DEFAULT.getValueForJava());
		Assertions.assertEquals("public", ConstructorAccessModifier.PUBLIC.getValueForJava());
		Assertions.assertEquals("private", ConstructorAccessModifier.PRIVATE.getValueForJava());
		Assertions.assertEquals("protected", ConstructorAccessModifier.PROTECTED.getValueForJava());
	}

}
