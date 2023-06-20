package com.ooml_codegen.compiler.generator;

import com.ooml_codegen.compiler.generator.languages.java.JavaGenerator;

public class GeneratorFactory {

	public static Generator create(GeneratorType type) {
		return switch (type) {
			case JAVA -> new JavaGenerator();
		};
	}

}
