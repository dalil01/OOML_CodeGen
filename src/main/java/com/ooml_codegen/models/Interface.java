package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.enums.modifiers.access.InterfaceAccessModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Interface implements IGeneration {
    private final String name;

    private final Package iPackage;

    private final InterfaceAccessModifier accessModifier;

    private final List<Attribute> attributes = new ArrayList<>();

    private final List<Method> methods = new ArrayList<>();

    public Interface(String name, Package cPackage, InterfaceAccessModifier accessModifier) {
        this.name = name;
        this.iPackage = iPackage;
        this.accessModifier = accessModifier;
    }

    public String getName() {
        return this.name;
    }

    public Package getPackage() {
        return this.iPackage;
    }

    public InterfaceAccessModifier getAccessModifier() {
        return this.accessModifier;
    }
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public boolean addMethod(Method method) {
        return this.methods.add(method);
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    @Override
    public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {
        return Map.of(
                GenerationContext.PACKAGE, this.iPackage.getName(),
                GenerationContext.CLASS_ACCESS_MODIFIER, (type == GeneratorType.JAVA) ? this.accessModifier.getValueForJava() : this.accessModifier.getValueForOOML(),
                GenerationContext.CLASS_NAME, this.name
        );
    }

    @Override
    public String getFileName() {
        return this.name;
    }
}
