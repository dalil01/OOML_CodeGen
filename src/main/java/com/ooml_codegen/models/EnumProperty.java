package com.ooml_codegen.models;

public class EnumProperty {
    private final String key;
    private String value = "";

    public EnumProperty(String key, String value ) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}
