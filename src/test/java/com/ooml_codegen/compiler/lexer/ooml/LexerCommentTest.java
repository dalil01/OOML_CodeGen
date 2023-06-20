package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerCommentTest extends LexerTest {

	public LexerCommentTest() {
		super("comment.ooml");
	}

	@Test
	public void singleLineCommentTest() {
		Token token = this.lexer.nextToken();
		while (token.getType() != TokenType.EOF){
			int currentToken = this.index.getAndIncrement();

			System.out.println(token.toString());

			if (List.of(1, 2, 3).contains(currentToken)) {
				Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.getType());
			}

			if (currentToken == 1) {
				Assertions.assertEquals(" Hello World!", token.getStringValue());
			}
			else if (currentToken == 2) {
				Assertions.assertEquals(" Lorem ipsum /* */", token.getStringValue());
			}
			else if (currentToken == 3) {
				Assertions.assertEquals("/    Test // Test", token.getStringValue());
			}
			token = this.lexer.nextToken();
		}
	}

	@Test
	public void multiLineCommentTest() {
		Token token = this.lexer.nextToken();
		while (token.getType() != TokenType.EOF){
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
			token = this.lexer.nextToken();
		}
	}

}
