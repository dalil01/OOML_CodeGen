package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.*;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class OOMLLexerTest {

    private final static String pathPrefix = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/lexer/ooml/files/";

    @Test
    public void singleLineCommentTest() throws FileNotFoundException {
        String filePath = pathPrefix + "single-line-comment.ooml";

        Lexer lexer = new OOMLLexer(filePath);

        AtomicInteger index = new AtomicInteger(1);
        lexer.tokenize().forEach(token -> {
            int currentLine = index.getAndIncrement();
            System.out.println(token.toString());

            if (currentLine == 1) {
                Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.type());
                Assertions.assertEquals(" Hello World!", token.value());
            }

            if (currentLine == 2) {
                Assertions.assertEquals(TokenType.SINGLE_LINE_COMMENT, token.type());
                Assertions.assertEquals("  Lorem ipsum", token.value());
            }

            if (currentLine == 2) {
                Assertions.assertEquals(token.type(), TokenType.SINGLE_LINE_COMMENT);
                Assertions.assertEquals("    Test // Test   ", token.value());
            }
        });
    }

    @Test
    public void multiLineCommentTest() throws FileNotFoundException {

    }

    @Test
    public void importsTest() throws FileNotFoundException {
        String filePath = pathPrefix + "imports.ooml";

        Lexer lexer = new OOMLLexer(filePath);

        AtomicInteger index = new AtomicInteger(1);
        lexer.tokenize().forEach(token -> {
            System.out.println(index.getAndIncrement() + " " + token.toString());
        });
    }


}
