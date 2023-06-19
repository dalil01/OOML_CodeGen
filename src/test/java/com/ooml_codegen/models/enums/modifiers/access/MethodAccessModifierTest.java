package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodAccessModifierTest {

	@Test
	public void getValueForOOMLTest() {
		Assertions.assertEquals("", MethodAccessModifier.DEFAULT.getValueForOOML());
		Assertions.assertEquals("+", MethodAccessModifier.PUBLIC.getValueForOOML());
		Assertions.assertEquals("-", MethodAccessModifier.PRIVATE.getValueForOOML());
		Assertions.assertEquals("#", MethodAccessModifier.PROTECTED.getValueForOOML());
	}

	@Test
	public void getValueForJavaTest() {
		Assertions.assertEquals("", MethodAccessModifier.DEFAULT.getValueForJava());
		Assertions.assertEquals("public", MethodAccessModifier.PUBLIC.getValueForJava());
		Assertions.assertEquals("private", MethodAccessModifier.PRIVATE.getValueForJava());
		Assertions.assertEquals("protected", MethodAccessModifier.PROTECTED.getValueForJava());
	}
}
