package com.ooml_codegen.utils.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColorCodeTest {

	@Test
	void resetToStringTest() {
		Assertions.assertEquals("\033[0m", ColorCode.RESET.toString());
	}

	@Test
	void greenStringTest() {
		Assertions.assertEquals("\u001B[32m", ColorCode.GREEN.toString());
	}

	@Test
	void yellowStringTest() {
		Assertions.assertEquals("\u001B[33m", ColorCode.YELLOW.toString());
	}

	@Test
	void redStringTest() {
		Assertions.assertEquals("\u001B[31m", ColorCode.RED.toString());
	}

}