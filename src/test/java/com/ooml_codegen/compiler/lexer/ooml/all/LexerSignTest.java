package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerSignTest extends LexerTest {

	public LexerSignTest() {
		super("sign.ooml");
	}

	@Test
	public void signTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 17, 20, 24).contains(i)) {
				Assertions.assertEquals(TokenType.SIGN, token.getType());
			}

			if (i == 8) {
				Assertions.assertNotEquals(TokenType.SIGN, token.getType());
			}

			if (List.of(1, 7, 8, 9, 17).contains(i)) {
				Assertions.assertEquals("+", token.getStringValue());
			}
			else if (List.of(3, 10, 11, 12, 20).contains(i)) {
				Assertions.assertEquals("-", token.getStringValue());
			}
			else if (List.of(5, 13, 14, 15, 24).contains(i)) {
				Assertions.assertEquals("#", token.getStringValue());
			}
		});
	}

}
