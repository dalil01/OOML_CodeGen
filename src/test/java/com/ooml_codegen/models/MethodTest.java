package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.AttributeAccessModifier;
import com.ooml_codegen.models.enums.ConstructorAccessModifier;
import com.ooml_codegen.models.enums.MethodAccessModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodTest {

    @Test
    public void constructorTest() {
        String expectedName = "name";
        Type expectedReturnType = new Type("string");

        Method method = new Method(expectedName, MethodAccessModifier.PUBLIC, expectedReturnType);

        Assertions.assertEquals(expectedName, method.getName());
        Assertions.assertEquals(expectedReturnType, method.getReturnType());
    }

//    @Test
//    public void getNameTest() {
//        String expectedName = "name";
//        Type type = new Type()
//    }

}
