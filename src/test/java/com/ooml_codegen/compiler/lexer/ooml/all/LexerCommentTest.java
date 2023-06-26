package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.*;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerCommentTest extends LexerTest {

	public LexerCommentTest() {
		super("comment.ooml");
	}

	@Test
	public void singleLineCommentTest() {
		this.lexer.forEach((i, token) -> {
			System.out.println(token);

			if (List.of(1, 2, 3).contains(i)) {
				Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.getType());
			}

			if (i == 1) {
				Assertions.assertEquals(" Hello World!", token.getValue());
			}
			else if (i == 2) {
				Assertions.assertEquals(" Lorem ipsum /* */", token.getValue());
			}
			else if (i == 3) {
				Assertions.assertEquals("/    Test // Test", token.getValue());
			}
		});
	}

	@Test
	public void multiLineCommentTest() {
		this.lexer.forEach((index, token) -> {
			System.out.println(token);

			if (index == 4) {
				Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.getType());
				Assertions.assertEquals("\n" +
						"\tHello Mundo!\n" +
						"\n" +
						"\t", normalizedString(token.getValue()));
			}

			if (index == 5) {
				Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.getType());
				Assertions.assertEquals("\n" +
						"\t/* Hi ! /\n" +
						"\t", normalizedString(token.getValue()));
			}
			else if (index == 6) {
				Assertions.assertEquals(token.getType(), TokenType.MULTI_LINE_COMMENT);
				Assertions.assertEquals(" OOML!!!    + - / * > # { }  :  ", normalizedString(token.getValue()));
			}
			else if (index == 7) {
				Assertions.assertEquals(token.getType(), TokenType.MULTI_LINE_COMMENT);
				Assertions.assertEquals(" Lorem\n" +
						"Ipsum\n" +
						"// 1\n" +
						"// 2\n" +
						"// 3\n" +
						"\n" +
						"\n" +
						"\n" +
						"\n", normalizedString(token.getValue()));
			}
		});
	}

}
