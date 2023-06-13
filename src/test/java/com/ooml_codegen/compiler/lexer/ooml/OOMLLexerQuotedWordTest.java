package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class OOMLLexerQuotedWordTest extends OOMLLexerTest {

    protected OOMLLexerQuotedWordTest() {
        super("quotedword.ooml");
    }

    @Test
    public void wordTest() throws FileNotFoundException {
        this.lexer.tokenize().forEach(token -> {
            int currentLine = this.index.getAndIncrement();

            System.out.println(token.toString());


            if (List.of(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19).contains(currentLine)) {
                Assertions.assertEquals(TokenType.WORD, token.type());
            }
            if (currentLine == 20) {
                Assertions.assertEquals(TokenType.EOF, token.type());
            } else if (currentLine == 1) {
                Assertions.assertEquals("he\"llo!", token.value());
            } else if (currentLine == 2) {
                Assertions.assertEquals("I \"'am", token.value());
            } else if (currentLine == 3) {
                Assertions.assertEquals("a", token.value());
            } else if (currentLine == 4) {
                Assertions.assertEquals("few words// I am", token.value());
            } else if (currentLine == 5) {
                Assertions.assertEquals("a", token.value());
            } else if (currentLine == 6) {
                Assertions.assertEquals("comment", token.value());
            } else if (currentLine == 7) {
                Assertions.assertEquals("c\"olo\\n:", token.value());
            } else if (currentLine == 8) {
                Assertions.assertEquals("import@tr", token.value());
            } else if (currentLine == 9) {
                Assertions.assertEquals("ash", token.value());
            } else if (currentLine == 10) {
                Assertions.assertEquals("comm", token.value());
            } else if (currentLine == 11) {
                Assertions.assertEquals("a,  z\n ", token.value());
            } else if (currentLine == 12) {
                Assertions.assertEquals("curlyb", token.value());
            } else if (currentLine == 13) {
                Assertions.assertEquals("rack", token.value());
            } else if (currentLine == 14) {
                Assertions.assertEquals("", token.value());
            } else if (currentLine == 15) {
                Assertions.assertEquals("et{}", token.value());
            } else if (currentLine == 16) {
                Assertions.assertEquals("this_is", token.value());
            } else if (currentLine == 17) {
                Assertions.assertEquals("$a|bi", token.value());
            } else if (currentLine == 18) {
                Assertions.assertEquals("g~wordé&%withµsome§hit", token.value());
            } else if (currentLine == 19) {
                Assertions.assertEquals("test", token.value());
            }
        });
    }
}
