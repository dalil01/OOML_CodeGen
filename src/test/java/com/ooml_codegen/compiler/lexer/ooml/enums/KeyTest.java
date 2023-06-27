package com.ooml_codegen.compiler.lexer.ooml.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyTest {

	@Test
	public void valuesTest() {
		OOMLKey[] expectedValues = {
				OOMLKey.NEWLINE,
				OOMLKey.SPACE,
				OOMLKey.QUOTE,
				OOMLKey.ACCESS_MODIFIER,
				OOMLKey.PARENTHESIS,
				OOMLKey.CURLY_BRACKET,
				OOMLKey.BRACKET,
				OOMLKey.PAD,
				OOMLKey.WORD_END,
				OOMLKey.FILE_END
		};

		OOMLKey[] actualValues = OOMLKey.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void getValueTest() {
		Assertions.assertEquals(
				OOMLSymbols.NEWLINE.toString() +
						OOMLSymbols.NEXT_LINE +
						OOMLSymbols.RETURN_CARRIAGE +
						OOMLSymbols.LINE_SEPARATOR +
						OOMLSymbols.PARAGRAPH_SEPARATOR,
				OOMLKey.NEWLINE.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.SPACE.toString() +
						OOMLSymbols.TABULATION,
				OOMLKey.SPACE.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.SINGLE_QUOTE.toString() +
						OOMLSymbols.DOUBLE_QUOTE +
						OOMLSymbols.BACK_QUOTE,
				OOMLKey.QUOTE.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.PLUS +
						OOMLSymbols.MINUS.toString() +
						OOMLSymbols.HASH,
				OOMLKey.ACCESS_MODIFIER.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.OPENING_PARENTHESIS.toString() +
						OOMLSymbols.CLOSING_PARENTHESIS,
				OOMLKey.PARENTHESIS.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.OPENING_CURLY_BRACKET.toString() +
						OOMLSymbols.CLOSING_CURLY_BRACKET,
				OOMLKey.CURLY_BRACKET.getValue()
		);

		Assertions.assertEquals(
				OOMLSymbols.OPENING_BRACKET.toString() +
						OOMLSymbols.CLOSING_BRACKET,
				OOMLKey.BRACKET.getValue()
		);

		Assertions.assertEquals(
				OOMLKey.SPACE.getValue() +
						OOMLKey.NEWLINE.getValue(),
				OOMLKey.PAD.getValue()
		);

		Assertions.assertEquals(
				OOMLKey.PAD.getValue() +
						OOMLSymbols.COLON +
						OOMLSymbols.SEMI_COLON +
						OOMLKey.QUOTE.getValue() +
						OOMLSymbols.EQUAL +
						OOMLSymbols.AT +
						OOMLKey.ACCESS_MODIFIER.getValue() +
						OOMLSymbols.COMMA +
						OOMLSymbols.SLASH +
						OOMLKey.CURLY_BRACKET.getValue() +
						OOMLKey.PARENTHESIS.getValue() +
						OOMLKey.BRACKET.getValue(),
				OOMLKey.WORD_END.getValue()
		);

		Assertions.assertEquals(
				OOMLKey.PAD.getValue() +
						OOMLKey.QUOTE.getValue() +
						OOMLSymbols.EQUAL +
						OOMLSymbols.AT +
						OOMLKey.ACCESS_MODIFIER.getValue() +
						OOMLSymbols.COMMA +
						OOMLKey.CURLY_BRACKET.getValue() +
						OOMLKey.PARENTHESIS.getValue() +
						OOMLKey.BRACKET.getValue(),
				OOMLKey.FILE_END.getValue()
		);
	}

}
