package com.ooml_codegen.compiler.generator.languages.java;

import com.ooml_codegen.compiler.generator.Generator;
import com.ooml_codegen.compiler.generator.GeneratorFactory;
import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Interface;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;

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
		Class clazz = new Class("User", new Package("com.ooml.models"), ClassAccessModifier.PRIVATE);

		Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
		generator.generate(clazz);
	}

}
