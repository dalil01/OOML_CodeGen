package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodAccessModifierTest {

	@Test
	public void valuesTest() {
		MethodAccessModifier[] expectedValues = {
				MethodAccessModifier.PUBLIC,
				MethodAccessModifier.PRIVATE,
				MethodAccessModifier.PROTECTED
		};

		MethodAccessModifier[] actualValues = MethodAccessModifier.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	void getValuePublicReturnsPlusTest() {
		Assertions.assertEquals('+', MethodAccessModifier.PUBLIC.getValue());
	}

	@Test
	void getValuePrivateReturnsMinusTest() {
		Assertions.assertEquals('-', MethodAccessModifier.PRIVATE.getValue());
	}

	@Test
	void getValueProtectedReturnsHashTest() {
		Assertions.assertEquals('#', MethodAccessModifier.PROTECTED.getValue());
	}

}
