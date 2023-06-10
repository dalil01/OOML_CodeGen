package com.ooml_codegen.compiler.lexer;

public enum TokenType {
    SINGLE_LINE_COMMENT,
    MULTI_LINE_COMMENT,
    IMPORT,
    PACKAGE,
    CLASS,
    INTERFACE,
    ENUM,
    INHERITANCE,
    ACCESS_MODIFIER,
    COLON,
    EQUAL,
    EOF
}