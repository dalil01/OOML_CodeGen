package com.ooml_codegen.models;

public class EnumProperty {
    private final String key;
    private final String value;

    public EnumProperty(String key ) {
        this.key = key;
        this.value = null;
    }
    public EnumProperty(String key, String value ) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return key + "(\"" + value + "\")";
        } else {
            return key;
        }
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}
