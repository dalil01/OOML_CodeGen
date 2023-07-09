package com.ooml_codegen.compiler.generator;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.generator.languages.java.JavaGenerator;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Enum;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ConstructorAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;
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
    public void testGenerateEnum() throws IOException {
        Enum anenum = new Enum("Color", new Package(filesPackage), EnumAccessModifier.PUBLIC);
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

        javaGenerator.setTemplate(pathPrefixVelocityTemplate + "Enum.vm");
        javaGenerator.generate(anenum);

        String generatedCode = new String(Files.readAllBytes(Paths.get(pathPrefixGeneratedFile + "Color.java")));
        String expectedCode = new String(Files.readAllBytes(Paths.get(pathPrefixTestTemplate + "Color.txt")));

        assertEquals(expectedCode, generatedCode);
    }


    @Test
}
