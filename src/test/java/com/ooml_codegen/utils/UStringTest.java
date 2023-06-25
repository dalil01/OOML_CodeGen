package com.ooml_codegen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UStringTest {

	@Test
	public void removeQuotesAroundTest() {
		String result;
		String input;
		String expected;

		result = UString.removeQuotesAround(null);
		Assertions.assertNull(result);

		result = UString.removeQuotesAround("");
		Assertions.assertEquals("", result);

		input = "Hello, world!";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(input, result);

		input = "'Hello, world!'";
		expected = "Hello, world!";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(expected, result);

		input = "\"Hello, world!\"";
		expected = "Hello, world!";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(expected, result);

		input = "\"\"Hello, world!\"";
		expected = "\"Hello, world!";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(expected, result);

		input = "\"Hello, world!\"\"";
		expected = "Hello, world!\"";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(expected, result);

		input = "He said, \"Hello, world!''";
		expected = "He said, \"Hello, world!'";
		result = UString.removeQuotesAround(input);
		Assertions.assertEquals(expected, result);
	}

}
