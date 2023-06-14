package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLKey {

    NEWLINE(OOMLSymbols.NEWLINE +
            OOMLSymbols.NEXT_LINE.toString() +
            OOMLSymbols.RETURN_CARRIAGE +
            OOMLSymbols.LINE_SEPARATOR +
            OOMLSymbols.PARAGRAPH_SEPARATOR),
    SPACE(OOMLSymbols.SPACE +
            OOMLSymbols.TABULATION.toString()),
    QUOTE(OOMLSymbols.SINGLE_QUOTE +
            OOMLSymbols.DOUBLE_QUOTE.toString() +
            OOMLSymbols.BACK_QUOTE),
    ACCESS_MODIFIER(OOMLSymbols.PLUS +
            OOMLSymbols.MINUS.toString() +
            OOMLSymbols.HASH),

    PARENTHESIS(OOMLSymbols.OPENING_PARENTHESIS +
            OOMLSymbols.CLOSING_PARENTHESIS.toString()),
    CURLY_BRACKET(OOMLSymbols.OPENING_CURLY_BRACKET +
            OOMLSymbols.CLOSING_CURLY_BRACKET.toString()),
    BRACKET(OOMLSymbols.OPENING_BRACKET +
            OOMLSymbols.CLOSING_BRACKET.toString()),

    PACKAGE_S("package"),

    PAD(SPACE.value + NEWLINE.value),

    WORD_END(PAD.value +
            OOMLSymbols.COLON +
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
            OOMLSymbols.COLON +
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
