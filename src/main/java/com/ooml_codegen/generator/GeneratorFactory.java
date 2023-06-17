package com.ooml_codegen.generator;

import com.ooml_codegen.generator.languages.java.JavaGenerator;

public class GeneratorFactory {

	public static Generator create(GeneratorType type) {
		return switch (type) {
			case JAVA -> new JavaGenerator();
		};
	}

}
