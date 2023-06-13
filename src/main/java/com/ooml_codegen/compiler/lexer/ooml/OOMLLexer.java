package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.CharStream;

import java.io.*;
import java.util.Objects;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

    private final CharStream cStream;

    public OOMLLexer(String filePath) throws FileNotFoundException {
        super(filePath);
        // TODO : Check file extension, must be .ooml
        this.cStream = new CharStream(new File(filePath));
    }

    public Stream<Token> tokenize() {
        Stream<Token> tokenStream = Stream.generate(this::nextToken).takeWhile(Objects::nonNull);
        tokenStream = Stream.concat(tokenStream, Stream.of(new Token(TokenType.EOF, null)));

        return tokenStream;
    }

    private Token nextToken() {
        consumePadding();
        return generateToken();
    }

    private Token generateToken() {
        if (cStream.isEOF()) {
            return null;
        }

        switch (cStream.getChar()) {
            case '/' -> {
                //Checking for the next character, might not be a comment
                cStream.next();

                if (cStream.isEOF()) {
                    return new Token(TokenType.WORD, "/");
                }

                if (cStream.getChar() != '*' && cStream.getChar() != '/') {
                    return generateWordToken("/");
                }

                return generateCommentToken();
            }
            case '@' -> {
                return generateImportToken();
            }
            case ':' -> {
                cStream.next();
                return new Token(TokenType.COLON, null);
            }
            case '=' -> {
                cStream.next();
                return new Token(TokenType.EQUAL, null);
            }
            case '+', '#' -> {
                Token tok = new Token(TokenType.SIGN, String.valueOf(cStream.getChar()));
                cStream.next();
                return tok;
            }
            case '-' -> {
                //check for next character to differentiate sign and inheritance
                cStream.next();

                if (cStream.getChar() == '>') {
                    //TODO Maybe add inherited stuff to this token
                    return new Token(TokenType.INHERITANCE, null);
                }

                return new Token(TokenType.SIGN, "-");
            }
            case '"', '\'', '`' -> {
                return generateQuotedWord();
            }
            default -> {
                return generateWordToken();
            }
        }
    }


    /**
     * Returns the current character,
     * Does not move the cursor,
     * returns -1 on error or EOF
     *//*
    private int peek() {

        if (!this.__charInUse) {

            try {
                this.__currentChar = this.reader.read();
                //System.out.println((char) this.__currentChar);

            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
                System.err.println("Assuming EOF after error, continuing...");
                this.__currentChar = -1;
                try {
                    reader.close();
                } catch (IOException ioe) {
                    System.err.println(ioe.getLocalizedMessage());
                    System.err.println("Failed while trying to close the reader, continuing...");
                }
            }
            this.__charInUse = true;
        }
        return this.__currentChar;
    }

    private char peek(int i){
        return 0;
    }

    /**
     * Returns the current character,
     * Moves the cursor by one forward (after the current character),
     * returns -1 on error or EOF
     *//*
    private int read() {
        if (this.__charInUse) {
            this.__charInUse = false;
            return this.__currentChar;
        }
        try {
            this.__currentChar = this.reader.read();
            //System.out.println((char) this.currentChar);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            System.err.println("Assuming EOF after error, continuing...");

            this.__currentChar = -1;
        }
        if (this.__currentChar == -1) {
            try {
                reader.close();

            } catch (IOException ioe) {
                System.err.println(ioe.getLocalizedMessage());
                System.err.println("Failed while trying to close the reader, continuing...");
            }
        }

        this.__charInUse = true;

        return this.__currentChar;

    }*/

    private void consumePadding() {
        while (!cStream.isEOF() && OOMLKey.PAD.getValue().indexOf(cStream.getChar()) != -1) {
            cStream.next();
        }
    }

    /**
     * We already know that the previous character is '/'
     * and that the current one is either '*' or '/'
     * @return Token
     */
    private Token generateCommentToken() {
        return (cStream.getChar() == '*') ? generateMultiLineCommentToken() : generateSingleLineCommentToken();
    }

    private Token generateSingleLineCommentToken() {
        cStream.next();

        StringBuilder s = new StringBuilder();
        while (!cStream.isEOF() && OOMLKey.NEWLINE.getValue().indexOf(cStream.getChar()) == -1) {
            s.append(cStream.getChar());
            cStream.next();
        }

        return new Token(TokenType.SINGLE_LINE_COMMENT, s.toString());
    }

    private Token generateMultiLineCommentToken() {
        cStream.next();

        StringBuilder s = new StringBuilder();
        while (true) {
            while (!cStream.isEOF() && cStream.getChar() != '*') {
                s.append(cStream.getChar());
                cStream.next();
            }

            // if we reached EOF (no '*')
            if (cStream.isEOF()) {
                break;
            }

            cStream.next();

            // someone added a '*' just before EOF
            if (cStream.isEOF()) {
                s.append('*');
                break;
            }

            if (cStream.getChar() == '/') {
                cStream.next();
                break;
            }

            s.append('*');

        }

        return new Token(TokenType.MULTI_LINE_COMMENT, s.toString());
    }

    /**
     * We are using a different word terminator here as file paths often contain '/'
     * @return Token
     */
    private Token generateImportToken() {
        cStream.next();
        consumePadding();

        if (cStream.isEOF()) {
            System.err.println("WARN: Import symbol found with nothing to import before EOF!");
            return new Token(TokenType.IMPORT, null);
        }

        String file;
        if (cStream.getChar() == '"' | cStream.getChar() == '\'' | cStream.getChar() == '`') {
            file = generateQuotedWord().value();
        } else {
            StringBuilder s = new StringBuilder();
            while (!cStream.isEOF() && OOMLKey.FILE_END.getValue().indexOf(cStream.getChar()) == -1) {
                s.append(cStream.getChar());
                cStream.next();
            }

            file = s.toString();
        }

        if (file == null || file.isEmpty()) {
            System.err.println("WARN: Import symbol found with nothing to import! Use quotes if a character isn't recognized as part of a file.");
            // using null objects instead of empty strings for easier checking... although isEmpty is O(1) tbh
            file = null;
        }

        return new Token(TokenType.IMPORT, file);
    }

    // TODO Need to implement proper Exception stuff
    private Token generateQuotedWord() {
        int quote = cStream.getChar();
        cStream.next();

        StringBuilder s = new StringBuilder();
        while (!cStream.isEOF() && cStream.getChar() != quote) {
            if (cStream.getChar() == '\\') {
                cStream.next();

                if (cStream.isEOF()){
                    System.err.println("Reached EOF after escaping character!");
                    break;
                }

                if (cStream.getChar() != quote && cStream.getChar() != '\\') {
                    System.err.println("Character '" + cStream.getChar() + "' did not need to be escaped.");
                }
            }
            s.append(cStream.getChar());
            cStream.next();
        }

        if (cStream.isEOF()){
            System.err.println("Quote closed by end of file.");
        } else {
            cStream.next();
        }

        return new Token(TokenType.WORD, s.toString());
    }

    private Token generateWordToken() {
        return generateWordToken("");
    }

    private Token generateWordToken(String prefix) {
        StringBuilder s = new StringBuilder(prefix + cStream.getChar());
        cStream.next();

        while (!cStream.isEOF() && OOMLKey.WORD_END.getValue().indexOf(cStream.getChar()) == -1) {
            s.append(cStream.getChar());
            cStream.next();
        }

        return new Token(TokenType.WORD, s.toString());
    }

}
