package com.ooml_codegen.models.enums.modifiers.access;

import com.ooml_codegen.models.enums.modifiers.access.ConstructorAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodAccessModifierTest {

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
