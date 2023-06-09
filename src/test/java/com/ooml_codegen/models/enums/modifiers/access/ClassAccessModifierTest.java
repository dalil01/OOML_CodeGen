package com.ooml_codegen.models.enums.modifiers.access;

import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassAccessModifierTest {

	@Test
	public void getValuePublicReturnsPlusTest() {
		Assertions.assertEquals('+', ClassAccessModifier.PUBLIC.getValue());
	}

	@Test
	public void getValuePrivateReturnsMinusTest() {
		Assertions.assertEquals('-', ClassAccessModifier.PRIVATE.getValue());
	}

}
