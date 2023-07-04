package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.enums.modifiers.access.InterfaceAccessModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interface implements IGeneration {
    private final String name;
    private final Package iPackage;
    private final InterfaceAccessModifier accessModifier;
    private final List<Constant> constants = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();

    public Interface(String name, Package iPackage, InterfaceAccessModifier accessModifier) {
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

    public boolean addMethod(Method method) {
        return this.methods.add(method);
    }
    public List<Method> getMethods() {
        return this.methods;
    }

    public boolean addConstant(Constant constant) {
        return this.constants.add(constant);
    }
    public List<Constant> getConstants() {
        return this.constants;
    }


    @Override
    public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {

        Map<GenerationContext, Object> map = new HashMap<>();

        map.put(GenerationContext.PACKAGE, this.iPackage.getName());
        map.put(GenerationContext.INTERFACE_ACCESS_MODIFIER, switch (type) {
            case JAVA -> this.accessModifier.getValueForJava();
            default -> this.accessModifier.getValueForOOML();
        });
        map.put(GenerationContext.INTERFACE_NAME, this.name);
        map.put(GenerationContext.METHODS, this.methods);
        map.put(GenerationContext.CONSTANTS, this.constants);
        return map;
    }

    @Override
    public String getFileName() {
        return this.name;
    }
}
