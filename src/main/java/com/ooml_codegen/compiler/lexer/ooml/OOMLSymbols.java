package com.ooml_codegen.compiler.lexer.ooml;

public enum OOMLSymbols {


    SLASH('/'),
    STAR('*'),
    BACKSLASH('\\'),
    NEWLINE('\n'),
    RETURN_CARRIAGE('\r'),
    LINE_SEPARATOR('\u2028'),
    PARAGRAPH_SEPARATOR('\u2029'),
    NEXT_LINE('\u0085'),
    SPACE(' '),
    TABULATION('\t'),
    SINGLE_QUOTE('\''),
    DOUBLE_QUOTE('"'),
    BACK_QUOTE('`'),
    EQUAL('='),
    PLUS('+'),
    MINUS('-'),
    HASH('#'),
    COMMA(','),
    COLON(':'),
    IMPORT('@'),
    GREATER_THAN('>'),
    OPENING_PARENTHESIS('('),
    CLOSING_PARENTHESIS(')'),
    OPENING_BRACKET('['),
    CLOSING_BRACKET('}'),
    OPENING_CURLY_BRACKET('{'),
    CLOSING_CURLY_BRACKET('}'),

    ;

    private final char value;

    OOMLSymbols(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }

    public String stringValue(){
        return String.valueOf(this.value);
    }

    public static OOMLSymbols getForChar(char c){
        for (OOMLSymbols symbol : OOMLSymbols.values()){
            if (c == symbol.value){
                return symbol;
            }
        }

        return null;
    }
}
