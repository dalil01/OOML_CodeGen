package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerWordTest extends LexerTest {

    protected LexerWordTest() {
        super("word.ooml");
    }

    @Test
    public void wordTest() {
        this.lexer.tokenize().forEach(token -> {
            int currentLine = this.index.getAndIncrement();

            System.out.println(token.toString());

            if (List.of(1, 2, 3, 5, 6, 8, 10, 12, 14, 17, 18).contains(currentLine)) {
                Assertions.assertEquals(TokenType.WORD, token.getType());
            }

            if (currentLine == 19){
                Assertions.assertEquals(TokenType.EOF, token.getType());
            }
            else if (currentLine == 1){
                Assertions.assertEquals("hello!", token.getStringValue());
            }
            else if (currentLine == 2){
                Assertions.assertEquals("I", token.getStringValue());
            }
            else if (currentLine == 3){
                Assertions.assertEquals("am", token.getStringValue());
            }
            else if (currentLine == 4){
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (currentLine == 5){
                Assertions.assertEquals("few", token.getStringValue());
            }
            else if (currentLine == 6){
                Assertions.assertEquals("words", token.getStringValue());
            }
            else if (currentLine == 8){
                Assertions.assertEquals("colon", token.getStringValue());
            }
            else if (currentLine == 10){
                Assertions.assertEquals("import", token.getStringValue());
            }
            else if (currentLine == 12){
                Assertions.assertEquals("comma", token.getStringValue());
            }
            else if (currentLine == 14){
                Assertions.assertEquals("curlybracket", token.getStringValue());
            }
            else if (currentLine == 17){
                Assertions.assertEquals("this_is$a|big~wordé&%withµsome§hit", token.getStringValue());
            }
            else if (currentLine == 18){
                Assertions.assertEquals("test", token.getStringValue());
            }
        });
    }

}
