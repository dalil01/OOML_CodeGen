package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.utils.Logger;

import java.io.*;
import java.util.Optional;

class OOMLLexer extends Lexer {


    public OOMLLexer(File file) throws FileNotFoundException {
        super(file);
    }

    public Token nextToken() {
        this.consumePadding();
        this.charN = this.cStream.getCharN();
        this.lineN = this.cStream.getLineN();
        Token tok = generateToken();
        if (tok == null){
            return new Token(TokenType.EOF, this.getFile().toPath(), this.lineN, this.lineN);
        }
        return tok;
    }

    /**
     * Discards all characters in the OOMLKey.PAD character set (considered PADDING)
     */
    private void consumePadding() {
        while (!this.cStream.isEOF() && OOMLKey.PAD.getValue().indexOf(this.cStream.getCurrentChar()) != -1) {
            this.cStream.next();
        }
    }

    private Token generateToken() {
        if (this.cStream.isEOF()) {
            return null;
        }

        // I don't know if the enum helped... actually seems less readable?
        Optional<OOMLSymbols> symbol = OOMLSymbols.getForChar(this.cStream.getCurrentChar());
        if (symbol.isEmpty()) {
            return this.generateWordToken();
        }

        switch (symbol.get()) {
            case SLASH -> {
                //Checking for the next character, might not be a comment
                this.cStream.next();

                if (this.cStream.isEOF()) {
                    return new Token(TokenType.WORD, OOMLSymbols.SLASH.toString(), this.getFile().toPath(), this.lineN, this.lineN);
                }

                if (this.cStream.getCurrentChar() != OOMLSymbols.STAR.getValue() && this.cStream.getCurrentChar() != OOMLSymbols.SLASH.getValue()) {
                    return this.generateWordToken(OOMLSymbols.SLASH.toString());
                }

                return this.generateCommentToken();
            }
            case COMMA -> {
                this.cStream.next();
                return new Token(TokenType.COMMA, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case IMPORT -> {
                return this.generateImportToken();
            }
            case COLON -> {
                this.cStream.next();
                return new Token(TokenType.COLON, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case SEMI_COLON -> {
                this.cStream.next();
                return new Token(TokenType.SEMI_COLON, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case EQUAL -> {
                this.cStream.next();
                return new Token(TokenType.EQUAL, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case PLUS, HASH -> {
                String character = String.valueOf(cStream.getCurrentChar());

                this.cStream.next();
                // check for next character to find access modifier block
                if (this.cStream.getCurrentChar() == OOMLSymbols.COLON.getValue()) {
                    // matched "+:" or "#:"
                    this.cStream.next();
                    return new Token(TokenType.ACCESS_MODIFIER_BLOCK, character, this.getFile().toPath(), this.lineN, this.lineN);
                }

                return new Token(TokenType.SIGN, character, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case MINUS -> {
                this.cStream.next();

                // check for next character to differentiate sign and inheritance
                if (this.cStream.getCurrentChar() == OOMLSymbols.GREATER_THAN.getValue()) {
                    // matched "->"
                    this.cStream.next();
                    return new Token(TokenType.INHERITANCE, this.getFile().toPath(), this.lineN, this.lineN);
                // check for next character to find access modifier block
                } else if (this.cStream.getCurrentChar() == OOMLSymbols.COLON.getValue()) {
                    // matched "-:"
                    this.cStream.next();
                    return new Token(TokenType.ACCESS_MODIFIER_BLOCK, OOMLSymbols.MINUS.toString(), this.getFile().toPath(), this.lineN, this.lineN);
                }

                return new Token(TokenType.SIGN, OOMLSymbols.MINUS.toString(), this.getFile().toPath(), this.lineN, this.lineN);
            }
            case DOUBLE_QUOTE, SINGLE_QUOTE, BACK_QUOTE -> {
                return this.generateQuotedWord();
            }
            case OPENING_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_BRACKET, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case CLOSING_BRACKET -> {
                cStream.next();
                return new Token(TokenType.CLOSING_BRACKET, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case OPENING_PARENTHESIS -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_PARENTHESIS, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case CLOSING_PARENTHESIS -> {
                this.cStream.next();
                return new Token(TokenType.CLOSING_PARENTHESIS, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case OPENING_CURLY_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.OPENING_CURLY_BRACKET, this.getFile().toPath(), this.lineN, this.lineN);
            }
            case CLOSING_CURLY_BRACKET -> {
                this.cStream.next();
                return new Token(TokenType.CLOSING_CURLY_BRACKET, this.getFile().toPath(), this.lineN, this.lineN);
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
        return (this.cStream.getCurrentChar() == OOMLSymbols.STAR.getValue()) ? this.generateMultiLineCommentToken() : this.generateSingleLineCommentToken();
    }

    private Token generateSingleLineCommentToken() {
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (!this.cStream.isEOF() && !containsChar(OOMLKey.NEWLINE.getValue(), this.cStream.getCurrentChar())) {
            s.append(this.cStream.getCurrentChar());
            this.cStream.next();
        }

        return new Token(TokenType.SINGLE_LINE_COMMENT, s.toString(), this.getFile().toPath(), this.lineN, this.lineN);
    }

    private Token generateMultiLineCommentToken() {
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (true) {
            while (!this.cStream.isEOF() && this.cStream.getCurrentChar() != OOMLSymbols.STAR.getValue()) {
                s.append(this.cStream.getCurrentChar());
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

            if (this.cStream.getCurrentChar() == OOMLSymbols.SLASH.getValue()) {
                this.cStream.next();
                break;
            }

            s.append(OOMLSymbols.STAR.getValue());
        }

        return new Token(TokenType.MULTI_LINE_COMMENT, s.toString(), this.getFile().toPath(), this.lineN, this.lineN);
    }

    /**
     * We are using a different word terminator here as file paths often contain '/'
     * @return Token
     */
    private Token generateImportToken() {
        this.cStream.next();
        this.consumePadding();

        if (this.cStream.isEOF()) {
            Logger.error("Import symbol found with nothing to import before EOF at " + this.getFile().toPath() + "@" + this.lineN + ":" + this.charN);
            return new Token(TokenType.IMPORT, this.getFile().toPath(), this.lineN, this.lineN);
        }

        Optional<String> optionalFilePath;
        if (this.cStream.getCurrentChar() == OOMLSymbols.DOUBLE_QUOTE.getValue() |
                this.cStream.getCurrentChar() == OOMLSymbols.SINGLE_QUOTE.getValue() |
                this.cStream.getCurrentChar() == OOMLSymbols.BACK_QUOTE.getValue()) {
            optionalFilePath = this.generateQuotedWord().getValue();
        } else {
            StringBuilder s = new StringBuilder();
            while (!this.cStream.isEOF() && !containsChar(OOMLKey.FILE_END.getValue(), this.cStream.getCurrentChar())) {
                s.append(this.cStream.getCurrentChar());
                this.cStream.next();
            }

            optionalFilePath = s.toString().describeConstable();
        }

        if (optionalFilePath.isEmpty()) {
            Logger.error("Import symbol found with nothing to import at " + this.getFile().toPath() + "@" + this.lineN + ":" + this.charN + "; Use quotes if a character isn't recognized as part of a file path.");
            return new Token(TokenType.IMPORT, this.getFile().toPath(), this.lineN, this.lineN);
        }

        return new Token(TokenType.IMPORT, optionalFilePath.get(), this.getFile().toPath(), this.lineN, this.lineN);
    }

    // TODO Need to implement proper Exception stuff
    private Token generateQuotedWord() {
        int quote = this.cStream.getCurrentChar();
        this.cStream.next();

        StringBuilder s = new StringBuilder();
        while (!this.cStream.isEOF() && this.cStream.getCurrentChar() != quote) {
            if (this.cStream.getCurrentChar() == OOMLSymbols.BACKSLASH.getValue()) {
                this.cStream.next();

                if (this.cStream.isEOF()) {
                    Logger.warn("Reached EOF after escaping character at " + this.getFile().toPath() + "@" + this.cStream.getLineN() + ":" + this.cStream.getLineN());
                    break;
                }

                if (this.cStream.getCurrentChar() != quote && this.cStream.getCurrentChar() != OOMLSymbols.BACKSLASH.getValue()) {
                    Logger.warn("Character '" + this.cStream.getCurrentChar() + "' did not need to be escaped at " + this.getFile().toPath() + "@" + this.cStream.getLineN() + ":" + this.cStream.getLineN());
                }
            }

            s.append(this.cStream.getCurrentChar());
            this.cStream.next();
        }

        if (this.cStream.isEOF()) {
            Logger.warn("Quote closed by end of file at " + this.getFile().toPath() + "@" + this.cStream.getLineN() + ":" + this.cStream.getLineN());
        } else {
            this.cStream.next();
        }

        return new Token(TokenType.QUOTED_WORD, s.toString(), this.getFile().toPath(), this.lineN, this.lineN);
    }

    private Token generateWordToken() {
        return this.generateWordToken("");
    }

    private Token generateWordToken(String prefix) {
        StringBuilder s = new StringBuilder(prefix + this.cStream.getCurrentChar());
        this.cStream.next();

        while (!this.cStream.isEOF() && !containsChar(OOMLKey.WORD_END.getValue(), this.cStream.getCurrentChar())) {
            s.append(this.cStream.getCurrentChar());
            this.cStream.next();
        }

        return new Token(TokenType.WORD, s.toString(), this.getFile().toPath(), this.lineN, this.lineN);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean containsChar(String characters, char c) {
        return characters.indexOf(c) != -1;
    }

}
