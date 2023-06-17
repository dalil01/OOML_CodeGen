package com.ooml_codegen.generator.languages.java;

import com.ooml_codegen.generator.Generator;
import com.ooml_codegen.generator.enums.GenerationContext;
import com.ooml_codegen.generator.interfaces.IGeneration;
import com.ooml_codegen.models.Class;

import java.util.Map;

public class JavaGenerator extends Generator {

	// TODO : Need meta data (java version, ...)
	@Override
	public void generate(IGeneration obj) {
		Map<GenerationContext, Object> context = obj.getGenerationContext();

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
		String templatePathPrefix = "java/";

		if (obj instanceof Class) {
			this.setTemplate(templatePathPrefix + "Class.vm");

			return true;
		}

		return false;
	}

}
