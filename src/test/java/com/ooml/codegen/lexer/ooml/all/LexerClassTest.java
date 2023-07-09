package com.ooml.codegen.lexer.ooml.all;

import com.ooml.codegen.lexer.TokenType;
import com.ooml.codegen.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerClassTest extends LexerTest {

	protected LexerClassTest() {
		super("class.ooml");
	}

	@Test
	public void classTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 5, 10).contains(i)) {
				Assertions.assertEquals(TokenType.CLASS, token.getType());
			}

			if (i == 2) {
				Assertions.assertEquals(token.getValue(), "User");
			} else if (i == 6) {
				Assertions.assertEquals(token.getValue(), "Person");
			} else if (i == 11) {
				Assertions.assertEquals(token.getValue(), "Address");
			}
		});
	}

}
