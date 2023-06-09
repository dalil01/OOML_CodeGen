package com.ooml.codegen.generator.java;

import com.ooml.codegen.generator.GenerationContext;
import com.ooml.codegen.generator.Generator;
import com.ooml.codegen.generator.GeneratorType;
import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.models.nodes.NClass;

import java.util.Map;

public class JavaGenerator extends Generator {

	// TODO : Need meta data (java version, ...)
	@Override
	public void generate(ICodeGenNode obj) {
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

	private boolean autoSetTemplate(ICodeGenNode obj) {
		String templatePathPrefix = "java/v20/";

		if (obj instanceof NClass) {
			this.setTemplate(templatePathPrefix + "Class.vm");

			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		/*Class clazz = new Class("User", new Package("com.ooml.models"), ClassAccessModifier.PRIVATE);

		Generator generator = GeneratorFactory.create(GeneratorType.JAVA);
		generator.generate(clazz);

		 */
	}

}
