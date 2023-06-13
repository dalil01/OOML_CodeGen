package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLKey {

    SINGLE_LINE_COMMENT("//"),
    MULTI_LINE_COMMENT_START("/*"),
    MULTI_LINE_COMMENT_END("*/"),
    ;

    private final String value;

    OOMLKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
