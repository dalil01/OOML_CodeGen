package com.ooml_codegen.compiler.generator.languages.java;

import com.ooml_codegen.compiler.generator.Generator;
import com.ooml_codegen.compiler.generator.GeneratorFactory;
import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.*;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.ConstantAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.InterfaceAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.MethodAccessModifier;

import java.util.Map;

public class JavaGenerator extends Generator {

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
			this.generateFile(obj.getFileName() + ".java");
		}
	}

	private boolean autoSetTemplate(IGeneration obj) {
		String templatePathPrefix = "java/v20/";

		if (obj instanceof Class) {
			this.setTemplate(templatePathPrefix + "Class.vm");

			return true;
		}
		if (obj instanceof Interface) {
			this.setTemplate(templatePathPrefix + "Interface.vm");

			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		Interface inter = new Interface("bonjour", new Package("com.ooml.models"), InterfaceAccessModifier.PUBLIC);
		Method method = new Method("getMethod", MethodAccessModifier.PUBLIC, new Type("String"));
		Method method2 = new Method("getMethod2", MethodAccessModifier.DEFAULT, new Type("String"));
		Constant constant1 = new Constant("age", new Type("int"), "10", ConstantAccessModifier.DEFAULT);

		inter.addMethod(method);
		inter.addMethod(method2);
		inter.addConstant(constant1);

		Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
		generator.generate(inter);
	}

}
