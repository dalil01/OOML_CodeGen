package com.ooml_codegen.compiler.generator;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.generator.languages.java.JavaGenerator;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Enum;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaGeneratorTest {

    private final static String pathPrefixGeneratedFile = System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/compiler/generator/files/";

    private final static String pathPrefixVelocityTemplate = "java/v20/";

    private final static String pathPrefixTestTemplate = "src/test/java/template/";

    private final static String filesPackage = "com.ooml_codegen.compiler.generator.files";

    private JavaGenerator javaGenerator;

    @BeforeEach
    public void setup() {
        this.javaGenerator = new JavaGenerator();
    }

    @Test
    public void testGenerateClass() throws IOException {
        Class clazz = new Class("User", new Package(filesPackage), ClassAccessModifier.PUBLIC);
        Constructor constructor = new Constructor("User", ConstructorAccessModifier.PUBLIC);

        clazz.addConstructor(constructor);

		Attribute attribute = new Attribute("attribute", AttributeAccessModifier.PUBLIC, new Type("int"), "5");
        Method method = new Method("methodTest", MethodAccessModifier.PUBLIC, new Type("int"));
        clazz.addAttribute(attribute);
        clazz.addMethod(method);

        javaGenerator.setTemplate(pathPrefixVelocityTemplate + "Class.vm");
        javaGenerator.generate(clazz);

        String generatedCode =  new String(Files.readAllBytes(Paths.get(pathPrefixGeneratedFile + "User.java")));
        String expectedCode = new String(Files.readAllBytes(Paths.get(pathPrefixTestTemplate + "User.txt")));

        assertEquals(expectedCode, generatedCode);
    }


    @Test
    public void testGenerateClass2() throws IOException {
        Constructor constructor = new Constructor("User", ConstructorAccessModifier.PUBLIC);

        Attribute attribute = new Attribute("attribute", AttributeAccessModifier.PUBLIC, new Type("String"), "value");

        Method method = new Method("getMethod", MethodAccessModifier.PUBLIC, new Type("String"));
        Method method2 = new Method("getMethod2", MethodAccessModifier.PUBLIC, new Type("String"));

        Parameter parameter = new Parameter("param", new Type("String"));

        Class clazz = new Class("User", new Package("com.ooml.models"), ClassAccessModifier.PUBLIC);

        clazz.addConstructor(constructor);
        clazz.addAttribute(attribute);
        clazz.addMethod(method);
        clazz.addMethod(method2);
        method.addParameter(parameter);

        Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
        generator.generate(clazz);
    }

//    @Test
//    public void testGenerateEnum() throws IOException {
//        Enum anenum = new Enum("Color", new Package(filesPackage), EnumAccessModifier.PUBLIC);
//        EnumProperty red = new EnumProperty("RED", "red");
//        EnumProperty orange = new EnumProperty("ORANGE", "orange");
//        EnumProperty yellow = new EnumProperty("YELLOW", "yellow");
//        EnumProperty green = new EnumProperty("GREEN", "green");
//        EnumProperty weight = new EnumProperty("WEIGH", "84.5");
//        EnumProperty blue = new EnumProperty("BLUE");
//
//        anenum.addEnumeration(red);
//        anenum.addEnumeration(orange);
//        anenum.addEnumeration(yellow);
//        anenum.addEnumeration(green);
//        anenum.addEnumeration(weight);
//        anenum.addEnumeration(blue);
//
//        javaGenerator.setTemplate(pathPrefixVelocityTemplate + "Enum.vm");
//        javaGenerator.generate(anenum);
//
//        String generatedCode = new String(Files.readAllBytes(Paths.get(pathPrefixGeneratedFile + "Color.java")));
//        String expectedCode = new String(Files.readAllBytes(Paths.get(pathPrefixTestTemplate + "Color.txt")));
//
//        assertEquals(expectedCode, generatedCode);
//    }


    @Test
    public void testGenerateInterface() throws IOException {
        Method method = new Method("getMethod", MethodAccessModifier.PUBLIC, new Type("String"));
        Method method2 = new Method("getMethod2", MethodAccessModifier.PUBLIC, new Type("String"));

        Interface inter = new Interface("Inter", new Package(filesPackage), InterfaceAccessModifier.PUBLIC);
        Constant constant1 = new Constant("age", new Type("int"), "10", ConstantAccessModifier.DEFAULT);

        inter.addMethod(method);
        inter.addMethod(method2);
        inter.addConstant(constant1);

        javaGenerator.setTemplate(pathPrefixVelocityTemplate + "Enum.vm");
        javaGenerator.generate(inter);

        String generatedCode = new String(Files.readAllBytes(Paths.get(pathPrefixGeneratedFile + "Inter.java")));
        String expectedCode = new String(Files.readAllBytes(Paths.get(pathPrefixTestTemplate + "Inter.txt")));

        assertEquals(expectedCode, generatedCode);
    }

}
