package com.ooml_codegen.compiler.lexer.ooml.enums;

public enum OOMLKey {

    NEWLINE(OOMLSymbols.NEWLINE.toString() +
            OOMLSymbols.NEXT_LINE +
            OOMLSymbols.RETURN_CARRIAGE +
            OOMLSymbols.LINE_SEPARATOR +
            OOMLSymbols.PARAGRAPH_SEPARATOR),
    SPACE(OOMLSymbols.SPACE.toString() +
            OOMLSymbols.TABULATION),
    QUOTE(OOMLSymbols.SINGLE_QUOTE.toString() +
            OOMLSymbols.DOUBLE_QUOTE +
            OOMLSymbols.BACK_QUOTE),
    ACCESS_MODIFIER(OOMLSymbols.PLUS +
            OOMLSymbols.MINUS.toString() +
            OOMLSymbols.HASH),

    PARENTHESIS(OOMLSymbols.OPENING_PARENTHESIS.toString() +
            OOMLSymbols.CLOSING_PARENTHESIS),
    CURLY_BRACKET(OOMLSymbols.OPENING_CURLY_BRACKET.toString() +
            OOMLSymbols.CLOSING_CURLY_BRACKET),
    BRACKET(OOMLSymbols.OPENING_BRACKET.toString() +
            OOMLSymbols.CLOSING_BRACKET),

    PAD(SPACE.value + NEWLINE.value),

    WORD_END(PAD.value +
            OOMLSymbols.COLON +
            OOMLSymbols.SEMI_COLON +
            QUOTE.value +
            OOMLSymbols.EQUAL +
            OOMLSymbols.IMPORT +
            ACCESS_MODIFIER.value +
            OOMLSymbols.COMMA +
            OOMLSymbols.SLASH +
            CURLY_BRACKET.value +
            PARENTHESIS.value +
            BRACKET.value),

    FILE_END(PAD.value +
            QUOTE.value +
            OOMLSymbols.EQUAL +
            OOMLSymbols.IMPORT +
            ACCESS_MODIFIER.value +
            OOMLSymbols.COMMA +
            CURLY_BRACKET.value +
            PARENTHESIS.value +
            BRACKET.value),
    ;

    private final String value;

    OOMLKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
