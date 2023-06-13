package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class OOMLLexerWordTest extends OOMLLexerTest{

    protected OOMLLexerWordTest() {
        super("word.ooml");
    }

    @Test
    public void wordTest() throws FileNotFoundException{
        this.lexer.tokenize().forEach(token -> {
            int currentLine = this.index.getAndIncrement();

            System.out.println(token.toString());


            if (List.of(1, 2, 3, 5, 6, 8, 10, 12, 13, 14, 15, 16, 17).contains(currentLine)) {
                Assertions.assertEquals(TokenType.WORD, token.type());
            }
            if (currentLine == 18){
                Assertions.assertEquals(TokenType.EOF, token.type());
            }
            else if (currentLine == 1){
                Assertions.assertEquals("hello!", token.value());
            }
            else if (currentLine == 2){
                Assertions.assertEquals("I", token.value());
            }
            else if (currentLine == 3){
                Assertions.assertEquals("am", token.value());
            }
            else if (currentLine == 4){
                Assertions.assertEquals("a", token.value());
            }
            else if (currentLine == 5){
                Assertions.assertEquals("few", token.value());
            }
            else if (currentLine == 6){
                Assertions.assertEquals("words", token.value());
            }
            else if (currentLine == 8){
                Assertions.assertEquals("colon", token.value());
            }
            else if (currentLine == 10){
                Assertions.assertEquals("import", token.value());
            }
            else if (currentLine == 12){
                Assertions.assertEquals("comma", token.value());
            }
            else if (currentLine == 13){
                Assertions.assertEquals("curlybracket", token.value());
            }
            else if (currentLine == 14){
                Assertions.assertEquals("{", token.value());
            }
            else if (currentLine == 15){
                Assertions.assertEquals("}", token.value());
            }
            else if (currentLine == 16){
                Assertions.assertEquals("this_is$a|big~wordé&%withµsome§hit", token.value());
            }
            else if (currentLine == 17){
                Assertions.assertEquals("test", token.value());
            }
        });
    }
}
