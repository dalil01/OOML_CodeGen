package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLKey {

    NEWLINE("\n\r\u2028\u2029\u0085"),
    SPACE(" \t"),
    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    BACK_QUOTE("`"),
    QUOTE(SINGLE_QUOTE.value + DOUBLE_QUOTE.value + BACK_QUOTE.value),
    EQUAL("="),
    ACCESS_MODIFIER("+-#"),
    INHERITANCE("->"),
    COMMA(","),
    COLON(":"),
    IMPORT("@"),

    PACKAGE_S("package"),

    PAD(SPACE.value + NEWLINE.value),

    WORD_END(PAD.value + COLON.value +
            QUOTE.value + EQUAL.value +
            IMPORT.value + ACCESS_MODIFIER.value + COMMA.value + "/{()}[]"),

    FILE_END(PAD.value + COLON.value +
            QUOTE.value + EQUAL.value +
            IMPORT.value + ACCESS_MODIFIER.value + COMMA.value + "{()}[]"),
    ;

    private final String value;

    OOMLKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public char getCharValue() {
        return this.value.charAt(0);
    }

}
