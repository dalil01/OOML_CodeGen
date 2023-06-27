package com.ooml_codegen.compiler.lexer;

public enum TokenType {

    SINGLE_LINE_COMMENT,
    MULTI_LINE_COMMENT,
    IMPORT,
    PACKAGE,
    CLASS,
    ENUM,
    INTERFACE,
    CLASS_INHERITANCE,
    INTERFACE_INHERITANCE,
    COLON,
    SEMI_COLON,
    EQUAL,
    SIGN,
    QUOTED_WORD,
    WORD,
    COMMA,
    OPENING_PARENTHESIS,
    CLOSING_PARENTHESIS,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    OPENING_CURLY_BRACKET,
    CLOSING_CURLY_BRACKET,
    ACCESS_MODIFIER,
    EOF

}
