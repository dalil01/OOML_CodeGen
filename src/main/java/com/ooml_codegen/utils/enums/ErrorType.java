package com.ooml_codegen.utils.enums;

public enum ErrorType {

    TRACE("TRACE"),
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR");

    private final String type;

    ErrorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}