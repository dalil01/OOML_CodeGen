package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.LexerTest;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerBlockTest extends LexerTest {

	public LexerBlockTest() {
		super("access_block.ooml");
	}

	@Test
	public void blockTest() {
		Token token = this.lexer.nextToken();
		while (token.getType() != TokenType.EOF){
			int currentToken = this.index.getAndIncrement();

			System.out.println(token);

			if (List.of(1, 3, 5, 6, 8, 10, 11, 13, 15, 16, 18, 20, 21, 22, 23, 24, 25, 31, 35).contains(currentToken)) {
				Assertions.assertEquals(TokenType.SIGN, token.getType());
			}

			if (List.of(19, 27, 29).contains(currentToken)) {
				Assertions.assertEquals(TokenType.ACCESS_MODIFIER_BLOCK, token.getType());
			} else {
				Assertions.assertNotEquals(TokenType.ACCESS_MODIFIER_BLOCK, token.getType());
			}

			if (currentToken == 19) {
				Assertions.assertEquals("#", token.getStringValue());
			}
			else if (currentToken == 27) {
				Assertions.assertEquals("+", token.getStringValue());
			}
			else if (currentToken == 29) {
				Assertions.assertEquals("-", token.getStringValue());
			}
			token = this.lexer.nextToken();
		}
	}

}
