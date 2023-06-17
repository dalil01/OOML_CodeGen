package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.LexerTest;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerInheritanceTest extends LexerTest {

	public LexerInheritanceTest() {
		super("inheritance.ooml");
	}

	@Test
	public void inheritanceTest() {
		this.lexer.tokenize().forEach(token -> {
			int currentToken = this.index.getAndIncrement();

			System.out.println(token.toString());

			if (List.of(3, 10, 16).contains(currentToken)) {
				Assertions.assertEquals(TokenType.INHERITANCE, token.getType());
			}
		});
	}

}
