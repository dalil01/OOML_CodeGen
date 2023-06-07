package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.ClassScope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassTest {

	private final String expectedName = "Address";
	private final Package expectedPackage = new Package("com.ooml_codegen.models");
	private ClassScope expectedScope = ClassScope.PUBLIC;

	private Class clazz;

	@BeforeEach
	public void setup() {
		this.clazz = new Class(this.expectedName, this.expectedPackage, this.expectedScope);
	}

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.clazz.getName());
		Assertions.assertEquals(this.expectedPackage, this.clazz.getPackage());
		Assertions.assertEquals(this.expectedScope, this.clazz.getScope());
	}

	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.clazz.getName());
	}

	public void getPackageTest() {
		Assertions.assertEquals(this.expectedPackage, clazz.getPackage());
	}

	public void getScopeTest() {
		this.expectedScope = ClassScope.PRIVATE;
		this.clazz = new Class(this.expectedName, this.expectedPackage, this.expectedScope);

		Assertions.assertEquals(this.expectedScope, this.clazz.getScope());
	}

	public void getAttributes() {
		// TODO
	}

}
