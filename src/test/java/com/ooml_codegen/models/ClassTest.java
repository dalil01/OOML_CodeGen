package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.ClassScope;
import com.ooml_codegen.models.enums.ConstructorScope;
import com.ooml_codegen.models.enums.MethodScope;
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

	public void addAttributeTest() {
		String expectedName = "Address";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addAttribute(new Attribute("id", new Type("string"))));
	}

	public void getAttributesTest() {
		String expectedName = "Address";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addAttribute(new Attribute("id" + i, new Type("string")));
		}

		Assertions.assertNotNull(clazz.getAttributes());
		Assertions.assertEquals(3, clazz.getAttributes().size());
		Assertions.assertEquals("id" + 2, clazz.getAttributes().get(1).getName());
	}

	public void addConstructorTest() {
		String expectedName = "Address";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addConstructor(new Constructor("id", ConstructorScope.PRIVATE)));
	}

	public void getConstructorsTest() {
		String expectedName = "User";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PRIVATE;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addConstructor(new Constructor("id" + i, ConstructorScope.PROTECTED));
		}

		Assertions.assertNotNull(clazz.getConstructors());
		Assertions.assertEquals(3, clazz.getConstructors().size());
		Assertions.assertEquals("id" + 3, clazz.getConstructors().get(2).getName());
	}

	public void addMethodTest() {
		String expectedName = "Person";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PUBLIC;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		Assertions.assertTrue(clazz.addMethod(new Method("id", MethodScope.PRIVATE, new Type("void"))));
	}

	public void getMethodsTest() {
		String expectedName = "User";
		Package expectedPackage = new Package("com.ooml_codegen.models");
		ClassScope expectedScope = ClassScope.PRIVATE;

		Class clazz = new Class(expectedName, expectedPackage, expectedScope);

		for (int i = 0; i < 3; i++) {
			clazz.addMethod(new Method("id", MethodScope.PRIVATE, new Type("void")));
		}

		Assertions.assertNotNull(clazz.getMethods());
		Assertions.assertEquals(3, clazz.getMethods().size());
		Assertions.assertEquals("id" + 1, clazz.getMethods().get(1).getName());
	}

}
