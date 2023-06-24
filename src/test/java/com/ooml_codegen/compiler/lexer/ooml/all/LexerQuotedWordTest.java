package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerQuotedWordTest extends LexerTest {

    protected LexerQuotedWordTest() {
        super("quoted-word.ooml");
    }

    @Test
    public void wordTest() {
        this.lexer.forEach((i, token) -> {
            System.out.println(token);

            if (List.of(1, 2, 7, 8, 11, 13, 14, 15, 17).contains(i)) {
                Assertions.assertEquals(TokenType.QUOTED_WORD, token.getType());
            }

            if (i == 20) {
                Assertions.assertEquals(TokenType.EOF, token.getType());
            }
            else if (i == 1) {
                Assertions.assertEquals("\"he\"llo!\"", token.getStringValue());
            }
            else if (i == 2) {
                Assertions.assertEquals("'I \"'am'", token.getStringValue());
            }
            else if (i == 3) {
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (i == 4) {
                Assertions.assertEquals("\"few words// I am\"", token.getStringValue());
            }
            else if (i == 5) {
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (i == 6) {
                Assertions.assertEquals("comment", token.getStringValue());
            }
            else if (i == 7) {
                Assertions.assertEquals("`c\"olo\\n:`", token.getStringValue());
            }
            else if (i == 8) {
                Assertions.assertEquals("\"import@tr\"", token.getStringValue());
            }
            else if (i == 9) {
                Assertions.assertEquals("ash", token.getStringValue());
            }
            else if (i == 10) {
                Assertions.assertEquals("comm", token.getStringValue());
            }
            else if (i == 11) {
                Assertions.assertEquals("\"a,  z\n \"", normalizedString(token.getStringValue()));
            }
            else if (i == 12) {
                Assertions.assertEquals("curlyb", token.getStringValue());
            }
            else if (i == 13) {
                Assertions.assertEquals("\"rack\"", token.getStringValue());
            }
            else if (i == 14) {
                Assertions.assertEquals("\"\"", token.getStringValue());
            }
            else if (i == 15) {
                Assertions.assertEquals("\"et{}\"", token.getStringValue());
            }
            else if (i == 16) {
                Assertions.assertEquals("this_is", token.getStringValue());
            }
            else if (i == 17) {
                Assertions.assertEquals("\"$a|bi\"", token.getStringValue());
            }
            else if (i == 18) {
                Assertions.assertEquals("g~wordé&%withµsome§hit", token.getStringValue());
            }
            else if (i == 19) {
                Assertions.assertEquals("test", token.getStringValue());
            }
        });
    }

}
