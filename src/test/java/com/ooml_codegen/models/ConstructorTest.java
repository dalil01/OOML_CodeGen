package com.ooml_codegen.models;

import com.ooml_codegen.models.modifiers.access.ConstructorAccessModifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstructorTest {

	private final String expectedName = "User";
	private final ConstructorAccessModifier accessModifier = ConstructorAccessModifier.PRIVATE;
	private Constructor constructor;

	@BeforeEach
	public void setup() {
		this.constructor = new Constructor(this.expectedName, this.accessModifier);
	}

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.constructor.getName());
		Assertions.assertEquals(this.accessModifier, this.constructor.getAccessModifier());
	}

	@Test
	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.constructor.getName());
	}

	@Test
	public void addParameterTest() {
		Assertions.assertTrue(this.constructor.addParameter(new Parameter("id", new Type("string"))));
	}

	@Test
	public void getParametersTest() {
		for (int i = 0; i < 3; i++) {
			this.constructor.addParameter(new Parameter("id" + 1, new Type("string")));
		}

		Assertions.assertNotNull(this.constructor.getParameters());
		Assertions.assertEquals(3, this.constructor.getParameters().size());
		Assertions.assertEquals("id" + 1, this.constructor.getParameters().get(1).getName());
	}

}
