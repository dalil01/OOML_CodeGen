package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerQuotedWordTest extends LexerTest {

    protected LexerQuotedWordTest() {
        super("quoted-word.ooml");
    }

    @Test
    public void wordTest() {
        this.lexer.tokenize().forEach(token -> {
            int currentToken = this.index.getAndIncrement();

            System.out.println(token.toString());

            if (List.of(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19).contains(currentToken)) {
                Assertions.assertEquals(TokenType.WORD, token.getType());
            }

            if (currentToken == 20) {
                Assertions.assertEquals(TokenType.EOF, token.getType());
            }
            else if (currentToken == 1) {
                Assertions.assertEquals("he\"llo!", token.getStringValue());
            }
            else if (currentToken == 2) {
                Assertions.assertEquals("I \"'am", token.getStringValue());
            }
            else if (currentToken == 3) {
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (currentToken == 4) {
                Assertions.assertEquals("few words// I am", token.getStringValue());
            }
            else if (currentToken == 5) {
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (currentToken == 6) {
                Assertions.assertEquals("comment", token.getStringValue());
            }
            else if (currentToken == 7) {
                Assertions.assertEquals("c\"olo\\n:", token.getStringValue());
            }
            else if (currentToken == 8) {
                Assertions.assertEquals("import@tr", token.getStringValue());
            }
            else if (currentToken == 9) {
                Assertions.assertEquals("ash", token.getStringValue());
            }
            else if (currentToken == 10) {
                Assertions.assertEquals("comm", token.getStringValue());
            }
            else if (currentToken == 11) {
                Assertions.assertEquals("a,  z\n ", normalizedString(token.getStringValue()));
            }
            else if (currentToken == 12) {
                Assertions.assertEquals("curlyb", token.getStringValue());
            }
            else if (currentToken == 13) {
                Assertions.assertEquals("rack", token.getStringValue());
            }
            else if (currentToken == 14) {
                Assertions.assertEquals("", token.getStringValue());
            }
            else if (currentToken == 15) {
                Assertions.assertEquals("et{}", token.getStringValue());
            }
            else if (currentToken == 16) {
                Assertions.assertEquals("this_is", token.getStringValue());
            }
            else if (currentToken == 17) {
                Assertions.assertEquals("$a|bi", token.getStringValue());
            }
            else if (currentToken == 18) {
                Assertions.assertEquals("g~wordé&%withµsome§hit", token.getStringValue());
            }
            else if (currentToken == 19) {
                Assertions.assertEquals("test", token.getStringValue());
            }
        });
    }

}
