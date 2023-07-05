package com.ooml_codegen.models.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassAccessModifierTest {

	@Test
	public void getValueForOOMLTest() {
		Assertions.assertEquals("", ClassAccessModifier.DEFAULT.getValueForOOML());
		Assertions.assertEquals("+", ClassAccessModifier.PUBLIC.getValueForOOML());
		Assertions.assertEquals("-", ClassAccessModifier.PRIVATE.getValueForOOML());
	}

	@Test
	public void getValueForJavaTest() {
		Assertions.assertEquals("", ClassAccessModifier.DEFAULT.getValueForJava());
		Assertions.assertEquals("public", ClassAccessModifier.PUBLIC.getValueForJava());
		Assertions.assertEquals("private", ClassAccessModifier.PRIVATE.getValueForJava());
	}

}