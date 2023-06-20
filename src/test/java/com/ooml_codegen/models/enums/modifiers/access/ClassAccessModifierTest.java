package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassAccessModifierTest {

	@Test
	public void valuesTest() {
		ClassAccessModifier[] expectedValues = {
				ClassAccessModifier.PUBLIC,
				ClassAccessModifier.PRIVATE
		};

		ClassAccessModifier[] actualValues = ClassAccessModifier.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void getValuePublicReturnsPlusTest() {
		Assertions.assertEquals('+', ClassAccessModifier.PUBLIC.getValue());
	}

	@Test
	public void getValuePrivateReturnsMinusTest() {
		Assertions.assertEquals('-', ClassAccessModifier.PRIVATE.getValue());
	}

}
