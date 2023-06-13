package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;

import java.io.*;
import java.util.Objects;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

    private int __currentChar = 0;
    private boolean __charInUse = false;

    // returns the character under the cursor but doesn't move


    public OOMLLexer(String filePath) {
        super(filePath);
    }

    public Stream<Token> tokenize() throws FileNotFoundException {
        try {
            // TODO : Check file extension, must be .ooml
            File file = new File(this.filePath);
            this.reader = new BufferedReader(new FileReader(file));

            Stream<Token> tokenStream = Stream.generate(this::nextToken).takeWhile(Objects::nonNull);
            tokenStream = Stream.concat(tokenStream, Stream.of(new Token(TokenType.EOF, null)));

            return tokenStream;
        } catch (FileNotFoundException e) {
            System.out.println("Lexer.tokenize: Error while opening file " + filePath + " ; " + e.getMessage());
            throw e;
        }
    }

    private Token nextToken() {

        // Consuming PADDING
        consumePadding();

        return generateToken();
    }

    private Token generateToken() {
        if (peek() == -1)
            return null;
        switch (peek()) {
            case '/' -> {
                //Checking for the next character, might not be a comment
                read();
                if (peek() == -1) {
                    return new Token(TokenType.WORD, "/");
                }
                if (peek() != '*' && peek() != '/') {
                    return generateWordToken("/");
                }
                return generateCommentToken();
            }
            case '@' -> {
                return generateImportToken();
            }
            case ':' -> {
                read();
                return new Token(TokenType.COLON, null);
            }
            case '=' -> {
                read();
                return new Token(TokenType.EQUAL, null);
            }
            case '+', '#' -> {
                return new Token(TokenType.SIGN, String.valueOf(read()));
            }
            case '-' -> {
                //check for next character to differentiate sign and inheritance
                read();
                if (peek() == '>') {
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
     */
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

    /**
     * Returns the current character,
     * Moves the cursor by one forward (after the current character),
     * returns -1 on error or EOF
     */
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

    }

    private void consumePadding() {
        while (peek() != -1 && OOMLKey.PAD.getValue().indexOf((char) peek()) != -1) {
            read();
        }
    }

    // we already know that the previous character is '/'
    // and that the current one is either '*' or '/'
    private Token generateCommentToken() {
        if (peek() == '*')
            return generateMultiLineCommentToken();
        return generateSingleLineCommentToken();
    }

    private Token generateSingleLineCommentToken() {
        read();
        StringBuilder s = new StringBuilder();
        while (peek() != -1 && OOMLKey.NEWLINE.getValue().indexOf((char) peek()) == -1) {
            s.append((char) read());
        }
        return new Token(TokenType.SINGLE_LINE_COMMENT, s.toString());
    }
    /*
    TODO
    isEOF();
    next();
    current();
    */

    /* zae* azea*/
    private Token generateMultiLineCommentToken() {
        read();
        StringBuilder s = new StringBuilder();
        while (true) {
            while (peek() != -1 && peek() != '*') {
                s.append((char) read());
            }
            // if we reached EOF (no '*')
            if (peek() == -1) {
                break;
            }
            read();

            // someone added a '*' just before EOF
            if (peek() == -1) {
                s.append('*');
                break;
            }

            if (peek() == '/') {
                read();
                break;
            }

            s.append('*');

        }
        return new Token(TokenType.MULTI_LINE_COMMENT, s.toString());
    }

    // We are using a different word terminator here as file paths often contain '/'
    private Token generateImportToken() {
        read();
        consumePadding();
        if (peek() == -1) {
            System.err.println("WARN: Import symbol found with nothing to import before EOF!");
            return new Token(TokenType.IMPORT, null);
        }
        String file;
        if (peek() == '"' | peek() == '\'' | peek() == '`') {
            file = generateQuotedWord().value();
        } else {
            StringBuilder s = new StringBuilder();
            while (peek() != -1 && OOMLKey.FILE_END.getValue().indexOf((char) peek()) == -1) {
                s.append((char) read());
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

    // Need to implement proper Exception stuff
    private Token generateQuotedWord() {
        int quote = read();
        StringBuilder s = new StringBuilder();
        while (peek() != -1 && peek() != quote) {
            if (peek() == '\\') {
                read();
                if (peek() != quote && peek() != '\\') {
                    System.err.println("Character '" + (char) peek() + "' did not need to be escaped.");
                }
            }
            s.append((char) read());
        }
        if (peek() == quote) {
            read();
        } else {
            System.err.println("Quote closed by end of file.");
        }
        return new Token(TokenType.WORD, s.toString());
    }

    private Token generateWordToken() {
        return generateWordToken("");
    }

    private Token generateWordToken(String prefix) {

        StringBuilder s = new StringBuilder(prefix);
        while (peek() != -1 && OOMLKey.WORD_END.getValue().indexOf((char) peek()) == -1) {
            s.append((char) read());
        }
        return new Token(TokenType.WORD, s.toString());
    }



}
