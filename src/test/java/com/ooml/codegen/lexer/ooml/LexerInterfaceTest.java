package com.ooml.codegen.lexer.ooml;

import com.ooml.codegen.lexer.ooml.LexerTest;
import com.ooml.codegen.lexer.Token.TokenType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerInterfaceTest extends LexerTest {

	protected LexerInterfaceTest() {
		super("interface.ooml");
	}

	@Test
	public void interfaceTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 5, 10).contains(i)) {
				Assertions.assertEquals(TokenType.INTERFACE, token.getType());
			}

			if (i == 2) {
				Assertions.assertEquals(token.getValue(), "IGeneration");
			} else if (i == 6) {
				Assertions.assertEquals(token.getValue(), "IServer");
			} else if (i == 11) {
				Assertions.assertEquals(token.getValue(), "I");
			}
		});
	}

}
