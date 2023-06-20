package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enum implements IGeneration {
    private final String name;
    private final Package ePackage;
    private final EnumAccessModifier accessModifier;
    private final List<EnumProperty> enumerations = new ArrayList<>();

    public Enum(String name, Package ePackage, EnumAccessModifier accessModifier) {
        this.name = name;
        this.ePackage = ePackage;
        this.accessModifier = accessModifier;
    }

    public String getName(){
        return this.name;
    }

    public Package getPackage(){
        return  this.ePackage;
    }

    public EnumAccessModifier getAccessModifier(){
        return this.accessModifier;
    }

    public boolean addEnumeration(EnumProperty enumeration) {
        return this.enumerations.add(enumeration);
    }

    public List<EnumProperty> getEnum() {
        return this.enumerations;
    }

    @Override
    public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {
        Map<GenerationContext, Object> map = new HashMap<>();

        map.put(GenerationContext.PACKAGE, this.ePackage.getName());
        map.put(GenerationContext.ENUMS_ACCESS_MODIFIER, switch (type){
            case JAVA ->  this.accessModifier.getValueForJava();
            default ->  this.accessModifier.getValueForOOML();
        });
        map.put(GenerationContext.ENUMS_NAME, this.name);

        return map;
    }

    @Override
    public String getFileName() {
        return this.name;
    }
}
