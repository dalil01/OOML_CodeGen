package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.ConstantAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

public class Constant {
    private final String name;
    private final Type type;
    private final String value;
    private final ConstantAccessModifier accessModifier;

    public Constant(String name, Type type, String value, ConstantAccessModifier accessModifier) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.accessModifier = accessModifier;
    }


    public String getName() {
        return this.name;
    }

    public Type getType() {return  this.type;}

    public String getValue() {return this.value;}

    public ConstantAccessModifier getAccessModifier() {
        return this.accessModifier;
    }

    public String toStringJava() {
        StringBuilder s = new StringBuilder();
        if (this.accessModifier != ConstantAccessModifier.DEFAULT) {
            s.append(this.accessModifier.getValueForJava()).append(" ");
        }
        s.append(this.type.getName()).append(" ").append(this.name);
        if (this.value != null) {
            s.append(" = ").append(this.value);
        }

        return s.toString();
    }
}
