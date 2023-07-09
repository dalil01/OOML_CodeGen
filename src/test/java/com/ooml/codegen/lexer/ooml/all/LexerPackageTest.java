package com.ooml.codegen.lexer.ooml.all;

import com.ooml.codegen.lexer.ooml.LexerTest;
import com.ooml.codegen.lexer.Token.TokenType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerPackageTest extends LexerTest {

	protected LexerPackageTest() {
		super("package.ooml");
	}


	@Test
	public void packageTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 3, 6).contains(i)) {
				Assertions.assertEquals(TokenType.PACKAGE, token.getType());
			}

			if (i == 2) {
				Assertions.assertEquals(token.getValue(), "com.ooml.package");
			} else if (i == 4) {
				Assertions.assertEquals(token.getValue(), "\"blabla.package.package\"");
			} else if (i == 7) {
				Assertions.assertEquals(token.getValue(), "dir.ooml.package");
			}
		});
	}

}
