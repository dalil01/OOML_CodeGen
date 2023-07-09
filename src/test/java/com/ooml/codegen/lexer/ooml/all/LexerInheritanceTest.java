package com.ooml.codegen.lexer.ooml.all;

import com.ooml.codegen.lexer.ooml.LexerTest;
import com.ooml.codegen.lexer.Token.TokenType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerInheritanceTest extends LexerTest {

	public LexerInheritanceTest() {
		super("inheritance.ooml");
	}

	@Test
	public void inheritanceTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(3, 10, 16).contains(i)) {
				Assertions.assertEquals(TokenType.CLASS_INHERITANCE, token.getType());
			}
		});
	}

}
