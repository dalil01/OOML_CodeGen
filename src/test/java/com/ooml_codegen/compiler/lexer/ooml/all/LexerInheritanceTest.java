package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
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
				Assertions.assertEquals(TokenType.INHERITANCE, token.getType());
			}
		});
	}

}
