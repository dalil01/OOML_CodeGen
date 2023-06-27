package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ConstructorAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassTest {

	private final String expectedName = "Address";
	private final Package expectedPackage = new Package("com.ooml_codegen.models");
	private ClassAccessModifier accessModifier = ClassAccessModifier.PUBLIC;

	private Class clazz;

	@BeforeEach
	public void setup() {
		//this.clazz = new Class(this.expectedName, this.expectedPackage, this.accessModifier);
	}

	/*

	@Test
	public void constructorTest() {
		Assertions.assertEquals(this.expectedName, this.clazz.getName());
		Assertions.assertEquals(this.expectedPackage, this.clazz.getPackage());
		Assertions.assertEquals(this.accessModifier, this.clazz.getAccessModifier());
	}

	public void getNameTest() {
		Assertions.assertEquals(this.expectedName, this.clazz.getName());
	}

	public void getPackageTest() {
		Assertions.assertEquals(this.expectedPackage, clazz.getPackage());
	}

	public void getAccessModifierTest() {
		this.accessModifier = ClassAccessModifier.PRIVATE;
		this.clazz = new Class(this.expectedName, this.expectedPackage, this.accessModifier);

		Assertions.assertEquals(this.accessModifier, this.clazz.getAccessModifier());
	}

	public void addAttributeTest() {
		String expectedName = "Address";
		AttributeAccessModifier accessModifier = AttributeAccessModifier.PRIVATE;
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addAttribute(new Attribute("id" + 1, accessModifier, new Type("string"))));
	}

	public void getAttributesTest() {
		String expectedName = "Address";
		AttributeAccessModifier accessModifier = AttributeAccessModifier.PRIVATE;
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addAttribute(new Attribute("id" + i, accessModifier, new Type("string")));
		}

		Assertions.assertNotNull(clazz.getAttributes());
		Assertions.assertEquals(3, clazz.getAttributes().size());
		Assertions.assertEquals("id" + 2, clazz.getAttributes().get(1).getName());
	}

	public void addConstructorTest() {
		String expectedName = "Address";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addConstructor(new Constructor("id", ConstructorAccessModifier.PRIVATE)));
	}

	public void getConstructorsTest() {
		String expectedName = "User";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PRIVATE;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addConstructor(new Constructor("id" + i, ConstructorAccessModifier.PROTECTED));
		}

		Assertions.assertNotNull(clazz.getConstructors());
		Assertions.assertEquals(3, clazz.getConstructors().size());
		Assertions.assertEquals("id" + 3, clazz.getConstructors().get(2).getName());
	}

	public void addMethodTest() {
		String expectedName = "Person";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addMethod(new Method("id", MethodAccessModifier.PRIVATE, new Type("void"))));
	}

	public void getMethodsTest() {
		String expectedName = "User";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassAccessModifier expectedScope = ClassAccessModifier.PRIVATE;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addMethod(new Method("id", MethodAccessModifier.PRIVATE, new Type("void")));
		}

		Assertions.assertNotNull(clazz.getMethods());
		Assertions.assertEquals(3, clazz.getMethods().size());
		Assertions.assertEquals("id" + 1, clazz.getMethods().get(1).getName());
	}

	 */

}