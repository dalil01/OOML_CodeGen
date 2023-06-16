package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.LexerTest;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerSignTest extends LexerTest {

	public LexerSignTest() {
		super("sign.ooml");
	}

	@Test
	public void signTest() {
		this.lexer.tokenize().forEach(token -> {
			int currentToken = this.index.getAndIncrement();

			System.out.println(token.toString());

			if (List.of(1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 17, 20, 24).contains(currentToken)) {
				Assertions.assertEquals(TokenType.SIGN, token.getType());
			}

			if (currentToken == 8) {
				Assertions.assertNotEquals(TokenType.SIGN, token.getType());
			}

			if (List.of(1, 7, 8, 9, 17).contains(currentToken)) {
				Assertions.assertEquals("+", token.getStringValue());
			}
			else if (List.of(3, 10, 11, 12, 20).contains(currentToken)) {
				Assertions.assertEquals("-", token.getStringValue());
			}
			else if (List.of(5, 13, 14, 15, 24).contains(currentToken)) {
				Assertions.assertEquals("#", token.getStringValue());
			}
		});
	}

}
