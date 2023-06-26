package com.ooml_codegen.compiler.generator.languages.java;

import com.ooml_codegen.compiler.generator.Generator;
import com.ooml_codegen.compiler.generator.GeneratorFactory;
import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.models.Enum;
import com.ooml_codegen.models.EnumProperty;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;
import com.ooml_codegen.models.enums.modifiers.access.EnumAccessModifier;

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
			this.generateFile( filePath + obj.getFileName() + ".java");
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

		return false;
	}

	public static void main(String[] args) {
		Class clazz = new Class("User", new Package("com.ooml.models"), ClassAccessModifier.PRIVATE);
		Enum anenum = new Enum("Color", new Package("com.ooml_java_generate_files"), EnumAccessModifier.PUBLIC);
		EnumProperty red = new EnumProperty("RED", "red");
		EnumProperty orange = new EnumProperty("ORANGE", "orange");
		EnumProperty yellow = new EnumProperty("YELLOW", "yellow");
		EnumProperty green = new EnumProperty("GREEN", "green");
		EnumProperty blue = new EnumProperty("BLUE");

		anenum.addEnumeration(red);
		anenum.addEnumeration(orange);
		anenum.addEnumeration(yellow);
		anenum.addEnumeration(green);
		anenum.addEnumeration(blue);

		Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
		//generator.generate(clazz);
		generator.generate(anenum);

	}

}
