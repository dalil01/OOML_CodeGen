package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.*;
import com.ooml_codegen.compiler.lexer.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerCommentTest extends LexerTest {

	public LexerCommentTest() {
		super("comment.ooml");
	}

	@Test
	public void singleLineCommentTest() {
		this.lexer.tokenize().forEach(token -> {
			int currentLine = this.index.getAndIncrement();

			System.out.println(token.toString());

			if (List.of(1, 2, 3).contains(currentLine)) {
				Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.getType());
			}

			if (currentLine == 1) {
				Assertions.assertEquals(" Hello World!", token.getStringValue());
			}
			else if (currentLine == 2) {
				Assertions.assertEquals(" Lorem ipsum /* */", token.getStringValue());
			}
			else if (currentLine == 3) {
				Assertions.assertEquals("/    Test // Test", token.getStringValue());
			}
		});
	}

	@Test
	public void multiLineCommentTest() {
		this.lexer.tokenize().forEach(token -> {
			int currentLine = this.index.getAndIncrement();

			if (currentLine == 4) {
				Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.getType());
				Assertions.assertEquals("\n" +
						"\tHello Mundo!\n" +
						"\n" +
						"\t", normalizedString(token.getStringValue()));
			}

			if (currentLine == 5) {
				Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.getType());
				Assertions.assertEquals("\n" +
						"\t/* Hi ! /\n" +
						"\t", normalizedString(token.getStringValue()));
			}
			else if (currentLine == 6) {
				Assertions.assertEquals(token.getType(), TokenType.MULTI_LINE_COMMENT);
				Assertions.assertEquals(" OOML!!!    + - / * > # { }  :  ", normalizedString(token.getStringValue()));
			}
			else if (currentLine == 7) {
				Assertions.assertEquals(token.getType(), TokenType.MULTI_LINE_COMMENT);
				Assertions.assertEquals(" Lorem\n" +
						"Ipsum\n" +
						"// 1\n" +
						"// 2\n" +
						"// 3\n" +
						"\n" +
						"\n" +
						"\n" +
						"\n", normalizedString(token.getStringValue()));
			}
		});
	}

}
