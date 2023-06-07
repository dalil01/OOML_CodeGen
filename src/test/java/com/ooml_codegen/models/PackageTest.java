package com.ooml_codegen.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PackageTest {

	private final String expectedName = "com.ooml_codegen.models";

	private Package pPackage;

	@BeforeEach
	public void setup() {
		this.pPackage = new Package(this.expectedName);
	}

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.pPackage.getName());
	}

	@Test
	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.pPackage.getName());
	}

}
