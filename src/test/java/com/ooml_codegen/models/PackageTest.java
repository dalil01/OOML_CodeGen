package com.ooml_codegen.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;

public class PackageTest {

    private final String expectedName = "name";
    private Package pPackage;

    @BeforeEach
    public void setup() {
        this.pPackage = new Package(this.expectedName);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(this.expectedName, pPackage.getName());
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals(this.expectedName, pPackage.getName());
    }
}
