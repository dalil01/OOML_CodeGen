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

        // I don't know if the enum helped... actually seems less readable?
        OOMLSymbols symbol = OOMLSymbols.getForChar(cStream.getChar());
        if (symbol == null){
            return generateWordToken();
        }

        switch (symbol) {
            case SLASH -> {
                //Checking for the next character, might not be a comment
                cStream.next();

                if (cStream.isEOF()) {
                    return new Token(TokenType.WORD, OOMLSymbols.SLASH.stringValue());
                }

                if (cStream.getChar() != OOMLSymbols.STAR.getValue() && cStream.getChar() != OOMLSymbols.SLASH.getValue()) {
                    return generateWordToken(OOMLSymbols.SLASH.stringValue());
                }

                return generateCommentToken();
            }
            case COMMA -> {
                cStream.next();
                return new Token(TokenType.COMMA, null);
            }
            case IMPORT -> {
                return generateImportToken();
            }
            case COLON -> {
                cStream.next();
                return new Token(TokenType.COLON, null);
            }
            case EQUAL -> {
                cStream.next();
                return new Token(TokenType.EQUAL, null);
            }
            case PLUS, HASH -> {
                Token tok = new Token(TokenType.SIGN, String.valueOf(cStream.getChar()));
                cStream.next();
                return tok;
            }
            case MINUS -> {
                //check for next character to differentiate sign and inheritance
                cStream.next();

                if (cStream.getChar() == OOMLSymbols.GREATER_THAN.getValue()) {
                    // matched "->"
                    //TODO Maybe add inherited stuff to this token
                    return new Token(TokenType.INHERITANCE, null);
                }

                return new Token(TokenType.SIGN, OOMLSymbols.MINUS.stringValue());
            }
            case DOUBLE_QUOTE, SINGLE_QUOTE, BACK_QUOTE -> {
                return generateQuotedWord();
            }
            case OPENING_BRACKET -> {
                cStream.next();
                return new Token(TokenType.OPENING_BRACKET, null);
            }
            case CLOSING_BRACKET -> {
                cStream.next();
                return new Token(TokenType.CLOSING_BRACKET, null);
            }
            case OPENING_PARENTHESIS -> {
                cStream.next();
                return new Token(TokenType.OPENING_PARENTHESIS, null);
            }
            case CLOSING_PARENTHESIS -> {
                cStream.next();
                return new Token(TokenType.CLOSING_PARENTHESIS, null);
            }
            case OPENING_CURLY_BRACKET -> {
                cStream.next();
                return new Token(TokenType.OPENING_CURLY_BRACKET, null);
            }
            case CLOSING_CURLY_BRACKET -> {
                cStream.next();
                return new Token(TokenType.CLOSING_CURLY_BRACKET, null);
            }
            default -> {
                return generateWordToken();
            }
        }
    }

    /**
     * Discards all characters in the OOMLKey.PAD character set (considered PADDING)
     */
    private void consumePadding() {
        while (!cStream.isEOF() && OOMLKey.PAD.getValue().indexOf(cStream.getChar()) != -1) {
            cStream.next();
        }
    }

    /**
     * Generates a token from any comment in a character stream
     * @return Token
     */
    private Token generateCommentToken() {
        // We already know that the previous character is '/'
        // and that the current one is either '*' or '/'
        return (cStream.getChar() == OOMLSymbols.STAR.getValue()) ? generateMultiLineCommentToken() : generateSingleLineCommentToken();
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
            while (!cStream.isEOF() && cStream.getChar() != OOMLSymbols.STAR.getValue()) {
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
                s.append(OOMLSymbols.STAR.getValue());
                break;
            }

            if (cStream.getChar() == OOMLSymbols.SLASH.getValue()) {
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
        if (cStream.getChar() == OOMLSymbols.DOUBLE_QUOTE.getValue() |
                cStream.getChar() == OOMLSymbols.SINGLE_QUOTE.getValue() |
                cStream.getChar() == OOMLSymbols.BACK_QUOTE.getValue()) {
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
            if (cStream.getChar() == OOMLSymbols.BACKSLASH.getValue()) {
                cStream.next();

                if (cStream.isEOF()){
                    System.err.println("Reached EOF after escaping character!");
                    break;
                }

                if (cStream.getChar() != quote && cStream.getChar() != OOMLSymbols.BACKSLASH.getValue()) {
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
