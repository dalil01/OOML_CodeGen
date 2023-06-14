package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.CharStream;

import java.io.*;
import java.util.Objects;
import java.util.Optional;
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
        tokenStream = Stream.concat(tokenStream, Stream.of(new Token(TokenType.EOF)));

        return tokenStream;
    }

    private Token nextToken() {
        this.consumePadding();
        return generateToken();
    }

    /**
     * Discards all characters in the OOMLKey.PAD character set (considered PADDING)
     */
    private void consumePadding() {
        while (!this.cStream.isEOF() && OOMLKey.PAD.getValue().indexOf(this.cStream.getChar()) != -1) {
            this. cStream.next();
        }
    }

    private Token generateToken() {
        if (this.cStream.isEOF()) {
            return null;
        }

        // I don't know if the enum helped... actually seems less readable?
        Optional<OOMLSymbols> symbol = OOMLSymbols.getForChar(this.cStream.getChar());
        if (symbol.isEmpty()){
            return this.generateWordToken();
        }

        switch (symbol.get()) {
            case SLASH -> {
                //Checking for the next character, might not be a comment
                this.cStream.next();

                if (this.cStream.isEOF()) {
                    return new Token(TokenType.WORD, OOMLSymbols.SLASH.toString());
                }

                if (this.cStream.getChar() != OOMLSymbols.STAR.getValue() && this.cStream.getChar() != OOMLSymbols.SLASH.getValue()) {
                    return this.generateWordToken(OOMLSymbols.SLASH.toString());
                }

                return this.generateCommentToken();
            }
            case COMMA -> {
                this.cStream.next();
                return new Token(TokenType.COMMA);
            }
            case IMPORT -> {
                return this.generateImportToken();
            }
            case COLON -> {
                this.cStream.next();
                return new Token(TokenType.COLON);
            }
            case EQUAL -> {
                this.cStream.next();
                return new Token(TokenType.EQUAL);
            }
            case PLUS, HASH -> {
                Token tok = new Token(TokenType.SIGN, String.valueOf(cStream.getChar()));
                this.cStream.next();
                return tok;
            }
            case MINUS -> {
                //check for next character to differentiate sign and inheritance
                this.cStream.next();

                if (this.cStream.getChar() == OOMLSymbols.GREATER_THAN.getValue()) {
                    // matched "->"
                    //TODO Maybe add inherited stuff to this token
                    return new Token(TokenType.INHERITANCE);
                }

                return new Token(TokenType.SIGN, OOMLSymbols.MINUS.toString());
            }
            case DOUBLE_QUOTE, SINGLE_QUOTE, BACK_QUOTE -> {
                return this.generateQuotedWord();
            }
            case OPENING_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_BRACKET);
            }
            case CLOSING_BRACKET -> {
                cStream.next();
                return new Token(TokenType.CLOSING_BRACKET);
            }
            case OPENING_PARENTHESIS -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_PARENTHESIS);
            }
            case CLOSING_PARENTHESIS -> {
                this.cStream.next();
                return new Token(TokenType.CLOSING_PARENTHESIS);
            }
            case OPENING_CURLY_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_CURLY_BRACKET);
            }
            case CLOSING_CURLY_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.CLOSING_CURLY_BRACKET);
            }
            default -> {
                return this.generateWordToken();
            }
        }
    }

    /**
     * Generates a token from any comment in a character stream
     * @return Token
     */
    private Token generateCommentToken() {
        // We already know that the previous character is '/'
        // and that the current one is either '*' or '/'
        return (this.cStream.getChar() == OOMLSymbols.STAR.getValue()) ? this.generateMultiLineCommentToken() : this.generateSingleLineCommentToken();
    }

    private Token generateSingleLineCommentToken() {
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (!this.cStream.isEOF() && !containsChar(OOMLKey.NEWLINE.getValue(), this.cStream.getChar())) {
            s.append(this.cStream.getChar());
            this.cStream.next();
        }

        return new Token(TokenType.SINGLE_LINE_COMMENT, s.toString());
    }

    private Token generateMultiLineCommentToken() {
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (true) {
            while (!this.cStream.isEOF() && this.cStream.getChar() != OOMLSymbols.STAR.getValue()) {
                s.append(this.cStream.getChar());
                this.cStream.next();
            }

            // if we reached EOF (no '*')
            if (this.cStream.isEOF()) {
                break;
            }

            this.cStream.next();

            // someone added a '*' just before EOF
            if (this.cStream.isEOF()) {
                s.append(OOMLSymbols.STAR.getValue());
                break;
            }

            if (this.cStream.getChar() == OOMLSymbols.SLASH.getValue()) {
                this.cStream.next();
                break;
            }

            s.append(OOMLSymbols.STAR.getValue());
        }

        return new Token(TokenType.MULTI_LINE_COMMENT, s.toString());
    }

    /**
     * We are using a different word terminator here as file paths often contain '/'
     * @return Token
     */
    private Token generateImportToken() {
        this.cStream.next();
        this.consumePadding();

        if (this.cStream.isEOF()) {
            System.err.println("WARN: Import symbol found with nothing to import before EOF!");
            return new Token(TokenType.IMPORT);
        }

        Optional<String> file;
        if (this.cStream.getChar() == OOMLSymbols.DOUBLE_QUOTE.getValue() |
                this.cStream.getChar() == OOMLSymbols.SINGLE_QUOTE.getValue() |
                this.cStream.getChar() == OOMLSymbols.BACK_QUOTE.getValue()) {
            file = this.generateQuotedWord().getValue();
        } else {
            StringBuilder s = new StringBuilder();
            while (!this.cStream.isEOF() && !containsChar(OOMLKey.FILE_END.getValue(), this.cStream.getChar())) {
                s.append(this.cStream.getChar());
                this.cStream.next();
            }

            file = s.toString().describeConstable();
        }

        if (file.isEmpty()) {
            System.err.println("WARN: Import symbol found with nothing to import! Use quotes if a character isn't recognized as part of a file.");
            // using null objects instead of empty strings for easier checking... although isEmpty is O(1) tbh
            return new Token(TokenType.IMPORT);
        }

        return new Token(TokenType.IMPORT, file.get());
    }

    // TODO Need to implement proper Exception stuff
    private Token generateQuotedWord() {
        int quote = this.cStream.getChar();
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (!this.cStream.isEOF() && this.cStream.getChar() != quote) {
            if (this.cStream.getChar() == OOMLSymbols.BACKSLASH.getValue()) {
                this.cStream.next();

                if (this.cStream.isEOF()){
                    System.err.println("Reached EOF after escaping character!");
                    break;
                }

                if (this.cStream.getChar() != quote && this.cStream.getChar() != OOMLSymbols.BACKSLASH.getValue()) {
                    System.err.println("Character '" + this.cStream.getChar() + "' did not need to be escaped.");
                }
            }

            s.append(this.cStream.getChar());
            this.cStream.next();
        }

        if (this.cStream.isEOF()){
            System.err.println("Quote closed by end of file.");
        } else {
            this.cStream.next();
        }

        return new Token(TokenType.WORD, s.toString());
    }

    private Token generateWordToken() {
        return this.generateWordToken("");
    }

    private Token generateWordToken(String prefix) {
        StringBuilder s = new StringBuilder(prefix + this.cStream.getChar());
        this.cStream.next();

        while (!this.cStream.isEOF() && !containsChar(OOMLKey.WORD_END.getValue(), this.cStream.getChar())) {
            s.append(this.cStream.getChar());
            this.cStream.next();
        }

        return new Token(TokenType.WORD, s.toString());
    }

    private static boolean containsChar(String characters, char c) {
        return characters.indexOf(c) != -1;
    }

}
