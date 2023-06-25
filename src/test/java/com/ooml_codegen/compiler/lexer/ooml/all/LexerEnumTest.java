package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerEnumTest extends LexerTest {

	protected LexerEnumTest() {
		super("enum.ooml");
	}

	@Test
	public void enumTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 5, 10).contains(i)) {
				Assertions.assertEquals(TokenType.ENUM, token.getType());
			}

			if (i == 2) {
				Assertions.assertEquals(token.getStringValue(), "DAY");
			} else if (i == 6) {
				Assertions.assertEquals(token.getStringValue(), "Type");
			} else if (i == 11) {
				Assertions.assertEquals(token.getStringValue(), "AccessModifier");
			}
		});
	}

}
