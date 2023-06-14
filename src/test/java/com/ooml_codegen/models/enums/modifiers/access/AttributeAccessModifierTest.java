package com.ooml_codegen.models.enums.modifiers.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AttributeAccessModifierTest {

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