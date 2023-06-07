package com.ooml_codegen.models.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstructorAccessModifierTest {

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
