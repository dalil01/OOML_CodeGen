package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLKey {
    NEWLINE("\n\r\u2028\u2029\u0085"),
    SPACE(" \t"),
    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    BACK_QUOTE("`"),
    QUOTE(SINGLE_QUOTE.value + DOUBLE_QUOTE.value + BACK_QUOTE.value),
    SINGLE_LINE_COMMENT_S("//"),
    SINGLE_LINE_COMMENT_CONTENT_R("[^" + NEWLINE.value +"]*"),
    MULTI_LINE_COMMENT_START_S("/*"),
    MULTI_LINE_COMMENT_CONTENT_R("([^*]|*[^/])*"), // Not needed?
    MULTI_LINE_COMMENT_END_S("*/"),
    EQUAL("="),
    ACCESS_MODIFIER("+-#"),
    INHERITANCE("->"),
    COMMA(","),
    COLON(":"),
    IMPORT("@"),

    PACKAGE_S("package"),

    SINGLE_QUOTED_WORD_R(SINGLE_QUOTE.value +
            "([^" + SINGLE_QUOTE.value + "]|\\\\" +
            SINGLE_QUOTE.value + ")*" +
            SINGLE_QUOTE.value
    ),
    DOUBLE_QUOTED_WORD_R(DOUBLE_QUOTE.value +
            "([^" + DOUBLE_QUOTE.value + "]|\\\\" +
            DOUBLE_QUOTE.value + ")*" +
            DOUBLE_QUOTE.value
    ),

    BACK_QUOTED_WORD_R(BACK_QUOTE.value +
            "([^" + BACK_QUOTE.value + "]|\\\\" +
            BACK_QUOTE.value + ")*" +
            BACK_QUOTE.value
    ),

    QUOTED_WORD_R(BACK_QUOTED_WORD_R.value + "|" + SINGLE_QUOTED_WORD_R.value + "|" + DOUBLE_QUOTED_WORD_R.value ),
    WORD_R("[^" + NEWLINE.value + SPACE.value + QUOTE.value + "]*"),

    PAD_R("[" + SPACE.value + NEWLINE.value + COMMA.value + "]*"),
    PAD(SPACE.value + NEWLINE.value + COMMA.value),

    WORD_END(PAD.value + COLON.value +
            QUOTE.value + EQUAL.value +
            IMPORT.value + ACCESS_MODIFIER.value + "/{("),

    FILE_END(PAD.value + COLON.value +
            QUOTE.value + EQUAL.value +
            IMPORT.value + ACCESS_MODIFIER.value + "{("),
    ;

    private final String value;

    OOMLKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
