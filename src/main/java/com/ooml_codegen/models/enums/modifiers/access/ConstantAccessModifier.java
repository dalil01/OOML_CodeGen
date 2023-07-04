package com.ooml_codegen.models.enums.modifiers.access;

public enum ConstantAccessModifier {
    DEFAULT,
    PUBLIC;

    public String getValueForOOML() {
        return switch (this) {
            case DEFAULT -> "";
            case PUBLIC -> "+";
        };
    }

    public String getValueForJava() {
        return switch (this) {
            case DEFAULT -> "";
            case PUBLIC -> "public";
        };
    }
}
