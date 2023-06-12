package com.ooml_codegen.utils;

public enum ColorCode {
    //Color end string, color reset
    RESET("\033[0m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m");

    private final String code;

    ColorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
