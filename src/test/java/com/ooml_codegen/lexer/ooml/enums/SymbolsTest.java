package com.ooml_codegen.lexer.ooml.enums;

import com.ooml_codegen.lexer.ooml.OOMLSymbols;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class SymbolsTest {

	@Test
	public void valuesTest() {
		OOMLSymbols[] expectedValues = {
				OOMLSymbols.SLASH,
				OOMLSymbols.STAR,
				OOMLSymbols.BACKSLASH,
				OOMLSymbols.NEWLINE,
				OOMLSymbols.RETURN_CARRIAGE,
				OOMLSymbols.LINE_SEPARATOR,
				OOMLSymbols.PARAGRAPH_SEPARATOR,
				OOMLSymbols.NEXT_LINE,
				OOMLSymbols.SPACE,
				OOMLSymbols.TABULATION,
				OOMLSymbols.SINGLE_QUOTE,
				OOMLSymbols.DOUBLE_QUOTE,
				OOMLSymbols.BACK_QUOTE,
				OOMLSymbols.EQUAL,
				OOMLSymbols.PLUS,
				OOMLSymbols.MINUS,
				OOMLSymbols.HASH,
				OOMLSymbols.COMMA,
				OOMLSymbols.COLON,
				OOMLSymbols.SEMI_COLON,
				OOMLSymbols.TIDLE,
				OOMLSymbols.AT,
				OOMLSymbols.GREATER_THAN,
				OOMLSymbols.OPENING_PARENTHESIS,
				OOMLSymbols.CLOSING_PARENTHESIS,
				OOMLSymbols.OPENING_BRACKET,
				OOMLSymbols.CLOSING_BRACKET,
				OOMLSymbols.OPENING_CURLY_BRACKET,
				OOMLSymbols.CLOSING_CURLY_BRACKET
		};

		OOMLSymbols[] actualValues = OOMLSymbols.values();

		Assertions.assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void testGetValue() {
		Assertions.assertEquals('/', OOMLSymbols.SLASH.getValue());
		Assertions.assertEquals('*', OOMLSymbols.STAR.getValue());
		Assertions.assertEquals('\\', OOMLSymbols.BACKSLASH.getValue());
		Assertions.assertEquals('\n', OOMLSymbols.NEWLINE.getValue());
		Assertions.assertEquals('\r', OOMLSymbols.RETURN_CARRIAGE.getValue());
		Assertions.assertEquals('\u2028', OOMLSymbols.LINE_SEPARATOR.getValue());
		Assertions.assertEquals('\u2029', OOMLSymbols.PARAGRAPH_SEPARATOR.getValue());
		Assertions.assertEquals('\u0085', OOMLSymbols.NEXT_LINE.getValue());
		Assertions.assertEquals(' ', OOMLSymbols.SPACE.getValue());
		Assertions.assertEquals('\t', OOMLSymbols.TABULATION.getValue());
		Assertions.assertEquals('\'', OOMLSymbols.SINGLE_QUOTE.getValue());
		Assertions.assertEquals('"', OOMLSymbols.DOUBLE_QUOTE.getValue());
		Assertions.assertEquals('`', OOMLSymbols.BACK_QUOTE.getValue());
		Assertions.assertEquals('=', OOMLSymbols.EQUAL.getValue());
		Assertions.assertEquals('+', OOMLSymbols.PLUS.getValue());
		Assertions.assertEquals('-', OOMLSymbols.MINUS.getValue());
		Assertions.assertEquals('#', OOMLSymbols.HASH.getValue());
		Assertions.assertEquals(',', OOMLSymbols.COMMA.getValue());
		Assertions.assertEquals(':', OOMLSymbols.COLON.getValue());
		Assertions.assertEquals(';', OOMLSymbols.SEMI_COLON.getValue());
		Assertions.assertEquals('@', OOMLSymbols.AT.getValue());
		Assertions.assertEquals('>', OOMLSymbols.GREATER_THAN.getValue());
		Assertions.assertEquals('(', OOMLSymbols.OPENING_PARENTHESIS.getValue());
		Assertions.assertEquals(')', OOMLSymbols.CLOSING_PARENTHESIS.getValue());
		Assertions.assertEquals('[', OOMLSymbols.OPENING_BRACKET.getValue());
		Assertions.assertEquals(']', OOMLSymbols.CLOSING_BRACKET.getValue());
		Assertions.assertEquals('{', OOMLSymbols.OPENING_CURLY_BRACKET.getValue());
		Assertions.assertEquals('}', OOMLSymbols.CLOSING_CURLY_BRACKET.getValue());
	}

	@Test
	public void testToString() {
		Assertions.assertEquals("/", OOMLSymbols.SLASH.toString());
		Assertions.assertEquals("*", OOMLSymbols.STAR.toString());
		Assertions.assertEquals("\\", OOMLSymbols.BACKSLASH.toString());
		Assertions.assertEquals("\n", OOMLSymbols.NEWLINE.toString());
		Assertions.assertEquals("\r", OOMLSymbols.RETURN_CARRIAGE.toString());
		Assertions.assertEquals("\u2028", OOMLSymbols.LINE_SEPARATOR.toString());
		Assertions.assertEquals("\u2029", OOMLSymbols.PARAGRAPH_SEPARATOR.toString());
		Assertions.assertEquals("\u0085", OOMLSymbols.NEXT_LINE.toString());
		Assertions.assertEquals(" ", OOMLSymbols.SPACE.toString());
		Assertions.assertEquals("\t", OOMLSymbols.TABULATION.toString());
		Assertions.assertEquals("'", OOMLSymbols.SINGLE_QUOTE.toString());
		Assertions.assertEquals("\"", OOMLSymbols.DOUBLE_QUOTE.toString());
		Assertions.assertEquals("`", OOMLSymbols.BACK_QUOTE.toString());
		Assertions.assertEquals("=", OOMLSymbols.EQUAL.toString());
		Assertions.assertEquals("+", OOMLSymbols.PLUS.toString());
		Assertions.assertEquals("-", OOMLSymbols.MINUS.toString());
		Assertions.assertEquals("#", OOMLSymbols.HASH.toString());
		Assertions.assertEquals(",", OOMLSymbols.COMMA.toString());
		Assertions.assertEquals(":", OOMLSymbols.COLON.toString());
		Assertions.assertEquals(";", OOMLSymbols.SEMI_COLON.toString());
		Assertions.assertEquals("@", OOMLSymbols.AT.toString());
		Assertions.assertEquals(">", OOMLSymbols.GREATER_THAN.toString());
		Assertions.assertEquals("(", OOMLSymbols.OPENING_PARENTHESIS.toString());
		Assertions.assertEquals(")", OOMLSymbols.CLOSING_PARENTHESIS.toString());
		Assertions.assertEquals("[", OOMLSymbols.OPENING_BRACKET.toString());
		Assertions.assertEquals("]", OOMLSymbols.CLOSING_BRACKET.toString());
		Assertions.assertEquals("{", OOMLSymbols.OPENING_CURLY_BRACKET.toString());
		Assertions.assertEquals("}", OOMLSymbols.CLOSING_CURLY_BRACKET.toString());
	}

	@Test
	public void testGetForChar() {
		Assertions.assertEquals(Optional.of(OOMLSymbols.SLASH), OOMLSymbols.getForChar('/'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.STAR), OOMLSymbols.getForChar('*'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.BACKSLASH), OOMLSymbols.getForChar('\\'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.NEWLINE), OOMLSymbols.getForChar('\n'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.RETURN_CARRIAGE), OOMLSymbols.getForChar('\r'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.LINE_SEPARATOR), OOMLSymbols.getForChar('\u2028'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.PARAGRAPH_SEPARATOR), OOMLSymbols.getForChar('\u2029'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.NEXT_LINE), OOMLSymbols.getForChar('\u0085'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.SPACE), OOMLSymbols.getForChar(' '));
		Assertions.assertEquals(Optional.of(OOMLSymbols.TABULATION), OOMLSymbols.getForChar('\t'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.SINGLE_QUOTE), OOMLSymbols.getForChar('\''));
		Assertions.assertEquals(Optional.of(OOMLSymbols.DOUBLE_QUOTE), OOMLSymbols.getForChar('"'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.BACK_QUOTE), OOMLSymbols.getForChar('`'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.EQUAL), OOMLSymbols.getForChar('='));
		Assertions.assertEquals(Optional.of(OOMLSymbols.PLUS), OOMLSymbols.getForChar('+'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.MINUS), OOMLSymbols.getForChar('-'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.HASH), OOMLSymbols.getForChar('#'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.COMMA), OOMLSymbols.getForChar(','));
		Assertions.assertEquals(Optional.of(OOMLSymbols.COLON), OOMLSymbols.getForChar(':'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.SEMI_COLON), OOMLSymbols.getForChar(';'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.AT), OOMLSymbols.getForChar('@'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.GREATER_THAN), OOMLSymbols.getForChar('>'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.OPENING_PARENTHESIS), OOMLSymbols.getForChar('('));
		Assertions.assertEquals(Optional.of(OOMLSymbols.CLOSING_PARENTHESIS), OOMLSymbols.getForChar(')'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.OPENING_BRACKET), OOMLSymbols.getForChar('['));
		Assertions.assertEquals(Optional.of(OOMLSymbols.CLOSING_BRACKET), OOMLSymbols.getForChar(']'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.OPENING_CURLY_BRACKET), OOMLSymbols.getForChar('{'));
		Assertions.assertEquals(Optional.of(OOMLSymbols.CLOSING_CURLY_BRACKET), OOMLSymbols.getForChar('}'));
		Assertions.assertEquals(Optional.empty(), OOMLSymbols.getForChar('A')); // Example for a character that is not a symbol
	}

}
