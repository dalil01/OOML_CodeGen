package com.ooml.codegen.utils.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ErrorTypeTest {

	@Test
	void resetToStringTest() {
		Assertions.assertEquals("TRACE", ErrorType.TRACE.toString());
	}

	@Test
	void debugToStringTest() {
		Assertions.assertEquals("DEBUG", ErrorType.DEBUG.toString());
	}

	@Test
	void infoToStringTest() {
		Assertions.assertEquals("INFO", ErrorType.INFO.toString());
	}

	@Test
	void warnToStringTest() {
		Assertions.assertEquals("WARN", ErrorType.WARN.toString());
	}

	@Test
	void errorToStringTest() {
		Assertions.assertEquals("ERROR", ErrorType.ERROR.toString());
	}

}