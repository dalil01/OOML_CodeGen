package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLKey {

    NEWLINE(OOMLSymbols.NEWLINE.stringValue() +
            OOMLSymbols.NEXT_LINE.stringValue() +
            OOMLSymbols.RETURN_CARRIAGE.stringValue() +
            OOMLSymbols.LINE_SEPARATOR.stringValue() +
            OOMLSymbols.PARAGRAPH_SEPARATOR.stringValue()),
    SPACE(OOMLSymbols.SPACE.stringValue() +
            OOMLSymbols.TABULATION.stringValue()),
    QUOTE(OOMLSymbols.SINGLE_QUOTE.stringValue() +
            OOMLSymbols.DOUBLE_QUOTE.stringValue() +
            OOMLSymbols.BACK_QUOTE.stringValue()),
    ACCESS_MODIFIER(OOMLSymbols.PLUS.stringValue() +
            OOMLSymbols.MINUS.stringValue() +
            OOMLSymbols.HASH.stringValue()),

    PARENTHESIS(OOMLSymbols.OPENING_PARENTHESIS.stringValue() +
            OOMLSymbols.CLOSING_PARENTHESIS.stringValue()),
    CURLY_BRACKET(OOMLSymbols.OPENING_CURLY_BRACKET.stringValue() +
            OOMLSymbols.CLOSING_CURLY_BRACKET.stringValue()),
    BRACKET(OOMLSymbols.OPENING_BRACKET.stringValue() +
            OOMLSymbols.CLOSING_BRACKET.stringValue()),

    PACKAGE_S("package"),

    PAD(SPACE.value + NEWLINE.value),

    WORD_END(PAD.value +
            OOMLSymbols.COLON.stringValue() +
            QUOTE.value +
            OOMLSymbols.EQUAL.stringValue() +
            OOMLSymbols.IMPORT.stringValue() +
            ACCESS_MODIFIER.value +
            OOMLSymbols.COMMA.stringValue() +
            OOMLSymbols.SLASH.stringValue() +
            CURLY_BRACKET.value +
            PARENTHESIS.value +
            BRACKET.value),

    FILE_END(PAD.value +
            OOMLSymbols.COLON.stringValue() +
            QUOTE.value +
            OOMLSymbols.EQUAL.stringValue() +
            OOMLSymbols.IMPORT.stringValue() +
            ACCESS_MODIFIER.value +
            OOMLSymbols.COMMA.stringValue() +
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
