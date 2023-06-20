package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumAccessModifierTest {
    @Test
    public void getValueForOOMLTest() {
        Assertions.assertEquals("", EnumAccessModifier.DEFAULT.getValueForOOML());
        Assertions.assertEquals("+", EnumAccessModifier.PUBLIC.getValueForOOML());
    }
    @Test
    public void getValueForJavaTest() {
        Assertions.assertEquals("", EnumAccessModifier.DEFAULT.getValueForJava());
        Assertions.assertEquals("public", EnumAccessModifier.PUBLIC.getValueForJava());
    }
}
