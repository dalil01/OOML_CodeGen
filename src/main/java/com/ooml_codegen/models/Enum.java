package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Enum implements IGeneration {
    private final String name;
    private final Package ePackage;
    private final EnumAccessModifier accessModifier;
    private final List<EnumProperty> enumerations = new ArrayList<>();

    private final boolean withValue = false;
    private final boolean withoutValue = false;

    public Enum(String name, Package ePackage, EnumAccessModifier accessModifier) {
        this.name = name;
        this.ePackage = ePackage;
        this.accessModifier = accessModifier;
    }

    public String getName() {
        return this.name;
    }

    public Package getPackage() {
        return this.ePackage;
    }

    public EnumAccessModifier getAccessModifier() {
        return this.accessModifier;
    }

    public boolean addEnumeration(EnumProperty enumeration) {
        return this.enumerations.add(enumeration);
    }

    public List<EnumProperty> getEnum() {
        return this.enumerations;
    }

    public String toStringEnums() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.enumerations.size(); i++) {
            if (i > 0) {
                sb.append("\t");
            }
            sb.append(this.enumerations.get(i));

            if (i < this.enumerations.size() - 1) {
                sb.append(",\n");
            }
        }

        return sb.toString();
    }

    @Override
    public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {
        Map<GenerationContext, Object> map = new HashMap<>();

        map.put(GenerationContext.PACKAGE, this.ePackage.getName());
        map.put(GenerationContext.ENUMS_ACCESS_MODIFIER, switch (type) {
            case JAVA -> this.accessModifier.getValueForJava();
            default -> this.accessModifier.getValueForOOML();
        });
        map.put(GenerationContext.ENUMS_NAME, this.name);
        map.put(GenerationContext.ENUMERATIONS, this.toStringEnums());
        map.put(GenerationContext.ENUM_WITH_VALUE, this.withValue);
        map.put(GenerationContext.ENUM_WITHOUT_VALUE, this.withoutValue);



        return map;
    }

    @Override
    public String getFileName() {
        return this.name;
    }
}
