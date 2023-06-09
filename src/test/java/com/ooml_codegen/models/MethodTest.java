package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodTest {

    private final String expectedName = "addUser";
    private final MethodAccessModifier expectedAccessModifier = MethodAccessModifier.PUBLIC;
    private final Type expectedReturnType = new Type("boolean");

    private Method method;

    @BeforeEach
    public void setup() {
        this.method = new Method(this.expectedName, this.expectedAccessModifier, this.expectedReturnType);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(this.expectedName, this.method.getName());
        Assertions.assertEquals(this.expectedAccessModifier, this.method.getAccessModifier());
        Assertions.assertEquals(this.expectedReturnType, this.method.getReturnType());
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals(this.expectedName, this.method.getName());
    }

    @Test
    public void getAccessModifierTest() {
        Assertions.assertEquals(this.expectedAccessModifier, this.method.getAccessModifier());
    }

    @Test
    public void getReturnTypeTest() {
        Assertions.assertEquals(this.expectedReturnType, this.method.getReturnType());
    }

    @Test
    public void addParameterTest() {
        Assertions.assertTrue(this.method.addParameter(new Parameter("id", new Type("string"))));
    }

    @Test
    public void getParametersTest() {
        for (int i = 0; i < 3; i++) {
            this.method.addParameter(new Parameter("id" + 1, new Type("string")));
        }

        Assertions.assertNotNull(this.method.getParameters());
        Assertions.assertEquals(3, this.method.getParameters().size());
        Assertions.assertEquals("id" + 1, this.method.getParameters().get(1).getName());
    }

}
