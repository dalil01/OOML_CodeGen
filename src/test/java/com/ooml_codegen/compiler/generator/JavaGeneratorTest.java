package com.ooml_codegen.compiler.generator;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.generator.languages.java.JavaGenerator;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ConstructorAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaGeneratorTest {

    private JavaGenerator javaGenerator;

    @BeforeEach
    public void setup() {
        this.javaGenerator = new JavaGenerator();
    }

    @Test
    public void testGenerate() throws IOException {
        Class clazz = new Class("User", new Package("com.ooml.models"), ClassAccessModifier.PUBLIC);
        Constructor constructor = new Constructor("User", ConstructorAccessModifier.PUBLIC);

        clazz.addConstructor(constructor);

        Method method = new Method("methodTest", MethodAccessModifier.PUBLIC, new Type("String"));
        clazz.addMethod(method);

        javaGenerator.setTemplate("java/v20/Class.vm");
        javaGenerator.generate(clazz);

        String generatedCode =  new String(Files.readAllBytes(Paths.get("User.java")));
        String expectedCode = new String(Files.readAllBytes(Paths.get("src/test/java/template/User.txt")));

        assertEquals(expectedCode, generatedCode);
    }
}
