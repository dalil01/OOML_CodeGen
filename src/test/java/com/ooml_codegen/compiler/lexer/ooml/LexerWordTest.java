package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Token;
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
        Token token = this.lexer.nextToken();
        while (token.getType() != TokenType.EOF){
            int currentToken = this.index.getAndIncrement();

            System.out.println(token.toString());

            if (List.of(1, 2, 3, 5, 6, 8, 10, 12, 14, 17, 18).contains(currentToken)) {
                Assertions.assertEquals(TokenType.WORD, token.getType());
            }

            if (currentToken == 19){
                Assertions.assertEquals(TokenType.EOF, token.getType());
            }
            else if (currentToken == 1){
                Assertions.assertEquals("hello!", token.getStringValue());
            }
            else if (currentToken == 2){
                Assertions.assertEquals("I", token.getStringValue());
            }
            else if (currentToken == 3){
                Assertions.assertEquals("am", token.getStringValue());
            }
            else if (currentToken == 4){
                Assertions.assertEquals("a", token.getStringValue());
            }
            else if (currentToken == 5){
                Assertions.assertEquals("few", token.getStringValue());
            }
            else if (currentToken == 6){
                Assertions.assertEquals("words", token.getStringValue());
            }
            else if (currentToken == 8){
                Assertions.assertEquals("colon", token.getStringValue());
            }
            else if (currentToken == 10){
                Assertions.assertEquals("import", token.getStringValue());
            }
            else if (currentToken == 12){
                Assertions.assertEquals("comma", token.getStringValue());
            }
            else if (currentToken == 14){
                Assertions.assertEquals("curlybracket", token.getStringValue());
            }
            else if (currentToken == 17){
                Assertions.assertEquals("this_is$a|big~wordé&%withµsome§hit", token.getStringValue());
            }
            else if (currentToken == 18){
                Assertions.assertEquals("test", token.getStringValue());
            }
            token = this.lexer.nextToken();
        }
    }

}
