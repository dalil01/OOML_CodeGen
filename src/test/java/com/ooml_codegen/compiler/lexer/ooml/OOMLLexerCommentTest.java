package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.*;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OOMLLexerCommentTest extends OOMLLexerTest {

    public OOMLLexerCommentTest() {
        super("comment.ooml");
    }

    @Test
    public void singleLineCommentTest() throws FileNotFoundException {
        this.lexer.tokenize().forEach(token -> {
            int currentLine = this.index.getAndIncrement();

            System.out.println(token.toString());

            if (List.of(1, 2, 3).contains(currentLine)) {
                Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.type());
            }

            if (currentLine == 1) {
                Assertions.assertEquals(" Hello World!", token.value());
            }
            else if (currentLine == 2) {
                Assertions.assertEquals(" Lorem ipsum /* */", token.value());
            }
            else if (currentLine == 3) {
                Assertions.assertEquals("/    Test // Test", token.value());
            }
        });
    }

    @Test
    public void multiLineCommentTest() throws FileNotFoundException {
        this.lexer.tokenize().forEach(token -> {
            int currentLine = this.index.getAndIncrement();

            if (currentLine == 4) {
                Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.type());
                Assertions.assertEquals("\n" +
                                "\tHello Mundo!\n" +
                                "\n" +
                                "\t", token.value());
            }

            if (currentLine == 5) {
                Assertions.assertEquals(TokenType.MULTI_LINE_COMMENT, token.type());
                Assertions.assertEquals("\n" +
                        "\t/* Hi ! /\n" +
                        "\t", token.value());
            }
            else if (currentLine == 6) {
                Assertions.assertEquals(token.type(), TokenType.MULTI_LINE_COMMENT);
                Assertions.assertEquals(" OOML!!!    + - / * > # { }  :  ", token.value());
            }
            else if (currentLine == 7) {
                //System.out.println(StringUtils.replace(token.value(), "\n", "$"));

                Assertions.assertEquals(token.type(), TokenType.MULTI_LINE_COMMENT);
                Assertions.assertEquals(" Lorem\n" +
                        "Ipsum\n" +
                        "// 1\n" +
                        "// 2\n" +
                        "// 3\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n", token.value());
            }
        });
    }

}
