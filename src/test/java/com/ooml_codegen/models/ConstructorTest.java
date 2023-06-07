package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.ConstructorAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstructorTest {

    private final String expectedName = "name";
    private Constructor constructor;
    private ConstructorAccessModifier constructorAccessModifier;

    @BeforeEach
    public void setup() {
        this.constructor = new Constructor(this.expectedName, this.constructorAccessModifier);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(this.expectedName, this.constructor.getName());
        Assertions.assertEquals(this.constructorAccessModifier, this.constructor.getAccessModifier());
    }

    @Test
    public void getScopeTest() {
        Assertions.assertEquals(this.constructorAccessModifier, this.constructor.getAccessModifier());
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals(this.expectedName, this.constructor.getName());
    }
}
