package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnumTest {
    private final String expectedName = "Color";
    private final Package expectedPackage = new Package("com.ooml_codegen.models");
    private final EnumAccessModifier accessModifier = EnumAccessModifier.PUBLIC;
    private Enum anenum;

    @BeforeEach
    public void setup() {
        this.anenum = new Enum(this.expectedName, this.expectedPackage, this.accessModifier);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(this.expectedName, this.anenum.getName());
        Assertions.assertEquals(this.expectedPackage, this.anenum.getPackage());
        Assertions.assertEquals(this.accessModifier, this.anenum.getAccessModifier());
    }
}
