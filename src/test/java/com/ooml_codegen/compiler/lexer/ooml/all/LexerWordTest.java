package com.ooml_codegen.compiler.lexer.ooml.all;

import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.LexerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerWordTest extends LexerTest {

    protected LexerWordTest() {
        super("word.ooml");
    }

    @Test
    public void wordTest() {
        this.lexer.forEach((i, token) -> {
            System.out.println(token);

            if (List.of(1, 2, 3, 5, 6, 8, 10, 12, 14, 17, 18).contains(i)) {
                Assertions.assertEquals(TokenType.WORD, token.getType());
            }

            if (i == 19){
                Assertions.assertEquals(TokenType.EOF, token.getType());
            }
            else if (i == 1){
                Assertions.assertEquals("hello!", token.getValue());
            }
            else if (i == 2){
                Assertions.assertEquals("I", token.getValue());
            }
            else if (i == 3){
                Assertions.assertEquals("am", token.getValue());
            }
            else if (i == 4){
                Assertions.assertEquals("a", token.getValue());
            }
            else if (i == 5){
                Assertions.assertEquals("few", token.getValue());
            }
            else if (i == 6){
                Assertions.assertEquals("words", token.getValue());
            }
            else if (i == 8){
                Assertions.assertEquals("colon", token.getValue());
            }
            else if (i == 10){
                Assertions.assertEquals("import", token.getValue());
            }
            else if (i == 12){
                Assertions.assertEquals("comma", token.getValue());
            }
            else if (i == 14){
                Assertions.assertEquals("curlybracket", token.getValue());
            }
            else if (i == 17){
                Assertions.assertEquals("this_is$a|big~wordé&%withµsome§hit", token.getValue());
            }
            else if (i == 18){
                Assertions.assertEquals("test", token.getValue());
            }
        });
    }

}
