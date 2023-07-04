package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void addEnumerationTest () {
        List<EnumProperty> enumerations = anenum.getEnum();
        EnumProperty red = new EnumProperty("RED", "red");
        EnumProperty blue = new EnumProperty("BLUE");
        Assertions.assertTrue(anenum.addEnumeration(red));
        Assertions.assertTrue(anenum.addEnumeration(blue));
        Assertions.assertEquals(2, anenum.getEnum().size());

        System.out.println("The list of enumerations => " + enumerations.toString());
    }
}
