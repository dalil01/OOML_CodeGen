package com.ooml.codegen.lexer.ooml.all;

import com.ooml.codegen.lexer.ooml.LexerTest;
import com.ooml.codegen.lexer.Token.TokenType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerAccessModifierTest extends LexerTest {

	public LexerAccessModifierTest() {
		super("access-modifier.ooml");
	}

	@Test
	public void blockTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 3, 5, 6, 8, 10, 11, 13, 15, 16, 18, 20, 21, 22, 23, 24, 25, 31, 35).contains(i)) {
				Assertions.assertEquals(TokenType.SIGN, token.getType());
			}

			if (List.of(19, 27, 29).contains(i)) {
				Assertions.assertEquals(TokenType.ACCESS_MODIFIER_BLOCK, token.getType());
			} else {
				Assertions.assertNotEquals(TokenType.ACCESS_MODIFIER_BLOCK, token.getType());
			}

			if (i == 19) {
				Assertions.assertEquals("#", token.getValue());
			}
			else if (i == 27) {
				Assertions.assertEquals("+", token.getValue());
			}
			else if (i == 29) {
				Assertions.assertEquals("-", token.getValue());
			}
		});
	}

}
