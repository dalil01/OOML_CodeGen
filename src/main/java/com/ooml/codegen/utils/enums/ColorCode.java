package com.ooml.codegen.utils.enums;

public enum ColorCode {

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