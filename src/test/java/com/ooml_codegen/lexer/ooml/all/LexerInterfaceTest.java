package com.ooml_codegen.lexer.ooml.all;

import com.ooml_codegen.lexer.TokenType;
import com.ooml_codegen.lexer.ooml.LexerTest;
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
				Assertions.assertEquals(token.getValue(), "ICodeGenNode");
			} else if (i == 6) {
				Assertions.assertEquals(token.getValue(), "IServer");
			} else if (i == 11) {
				Assertions.assertEquals(token.getValue(), "I");
			}
		});
	}

}
