package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstructorAccessModifierTest {

	@Test
	public void valuesTest() {
		ConstructorAccessModifier[] expectedValues = {
				ConstructorAccessModifier.PUBLIC,
				ConstructorAccessModifier.PRIVATE,
				ConstructorAccessModifier.PROTECTED
		};

		ConstructorAccessModifier[] actualValues = ConstructorAccessModifier.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	void getValuePublicReturnsPlusTest() {
		Assertions.assertEquals('+', ConstructorAccessModifier.PUBLIC.getValue());
	}

	@Test
	void getValuePrivateReturnsMinusTest() {
		Assertions.assertEquals('-', ConstructorAccessModifier.PRIVATE.getValue());
	}

	@Test
	void getValueProtectedReturnsHashTest() {
		Assertions.assertEquals('#', ConstructorAccessModifier.PROTECTED.getValue());
	}

}
