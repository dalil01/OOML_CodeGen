package com.ooml_codegen.compiler.generator.languages.java;

import com.ooml_codegen.compiler.generator.Generator;
import com.ooml_codegen.compiler.generator.GeneratorFactory;
import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Enum;
import com.ooml_codegen.models.EnumProperty;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ConstructorAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;
import org.w3c.dom.Attr;
import com.ooml_codegen.models.enums.modifiers.access.ConstantAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.InterfaceAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

import java.util.Map;

public class JavaGenerator extends Generator {

    String filePath = "./src/main/java/com/ooml_java_generate_files/";

    // TODO : Need meta data (java version, ...)
    @Override
    public void generate(IGeneration obj) {
        Map<GenerationContext, Object> context = obj.getGenerationContext(GeneratorType.JAVA);

        // TODO : Check & Add missing contexts & make reverse engineering to get existed file content & merge data
        // TODO : Think about the java version

        if (this.autoSetTemplate(obj)) {
            for (Map.Entry<GenerationContext, Object> c : context.entrySet()) {
                this.addContextToTemplate(c.getKey().getValue(), c.getValue());
            }

            // TODO : find file path from package
            this.generateFile(filePath + obj.getFileName() + ".java");
        }
    }

    private boolean autoSetTemplate(IGeneration obj) {
        String templatePathPrefix = "java/v20/";

        if (obj instanceof Class) {
            this.setTemplate(templatePathPrefix + "Class.vm");

            return true;
        }

        if (obj instanceof Enum) {
            this.setTemplate(templatePathPrefix + "Enum.vm");

            return true;
        }

        if (obj instanceof Interface) {
            this.setTemplate(templatePathPrefix + "Interface.vm");

            return true;
        }

        return false;
    }

	public static void main(String[] args) {

        Method method = new Method("getMethod", MethodAccessModifier.PUBLIC, new Type("String"));
        Method method2 = new Method("getMethod2", MethodAccessModifier.PUBLIC, new Type("String"));

        // class
		Constructor constructor = new Constructor("User", ConstructorAccessModifier.PUBLIC);
//		Attribute attribute = new Attribute("attribute", AttributeAccessModifier.PUBLIC, new Type("String"), "value");
		Class clazz = new Class("User", new Package("com.ooml_java_generate_files"), ClassAccessModifier.PUBLIC);

		clazz.addConstructor(constructor);
//		clazz.addAttribute(attribute);
//		clazz.addMethod(method);
//		clazz.addMethod(method2);

        // enum
        Enum anenum = new Enum("Color", new Package("com.ooml_java_generate_files"), EnumAccessModifier.PUBLIC);
        EnumProperty red = new EnumProperty("RED", "red");
        EnumProperty orange = new EnumProperty("ORANGE", "orange");
        EnumProperty yellow = new EnumProperty("YELLOW", "yellow");
        EnumProperty green = new EnumProperty("GREEN", "green");
        EnumProperty weight = new EnumProperty("WEIGH", "84.5");
        EnumProperty blue = new EnumProperty("BLUE");

        anenum.addEnumeration(red);
        anenum.addEnumeration(orange);
        anenum.addEnumeration(yellow);
        anenum.addEnumeration(green);
        anenum.addEnumeration(weight);
        anenum.addEnumeration(blue);

        // interface
        Interface inter = new Interface("Inter", new Package("com.ooml_java_generate_files"), InterfaceAccessModifier.PUBLIC);
        Constant constant1 = new Constant("age", new Type("int"), "10", ConstantAccessModifier.DEFAULT);

        inter.addMethod(method);
        inter.addMethod(method2);
        inter.addConstant(constant1);


        // generate
        Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
        generator.generate(clazz);
        generator.generate(anenum);
        generator.generate(inter);

	}

}
