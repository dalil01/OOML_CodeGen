package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeAccessModifierTest {

	@Test
	public void valuesTest() {
		AttributeAccessModifier[] expectedValues = {
				AttributeAccessModifier.PUBLIC,
				AttributeAccessModifier.PRIVATE,
				AttributeAccessModifier.PROTECTED
		};

		AttributeAccessModifier[] actualValues = AttributeAccessModifier.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void getValuePublicReturnsPlusTest() {
		Assertions.assertEquals('+', AttributeAccessModifier.PUBLIC.getValue());
	}

	@Test
	public void getValuePrivateReturnsMinusTest() {
		Assertions.assertEquals('-', AttributeAccessModifier.PRIVATE.getValue());
	}

	@Test
	public void getValueProtectedReturnsHashTest() {
		Assertions.assertEquals('#', AttributeAccessModifier.PROTECTED.getValue());
	}

}