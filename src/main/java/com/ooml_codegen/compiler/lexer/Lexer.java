package com.ooml_codegen.compiler.lexer;

import org.apache.velocity.runtime.directive.Foreach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Lexer {
    private final List<Token> tokenList;
    private final String filePath;

    public Lexer(String filePath) {

        this.tokenList = new ArrayList<>();
        this.filePath = filePath;

    }

    public static void main(String[] args) {
        Lexer lex = new Lexer("/home/celivalg/Dev/Epitech/OOML_CodeGen/src/main/resources/LexerTest.ooml");

        try {
            List<Token> l = lex.tokenize();
            int i = 0;
            for (Token t : l) {
                i++;
                System.out.println(t.toString() + " " + i);
            }
        } catch (FileNotFoundException e) {
        }


    }

    public List<Token> tokenize() throws FileNotFoundException {
        try {
            File file = new File(this.filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.tokenList.add(new Token(TokenType.CLASS, "Person"));
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Lexer.tokenize: Error while opening file " + filePath + " ; " + e.getMessage());
            throw e;
        }

        return this.tokenList;
    }

    public Token getNextToken() {
        return this.tokenList.remove(0);
    }

}
