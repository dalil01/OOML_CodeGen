package com.ooml.codegen.generator;

import com.ooml.codegen.generator.java.JavaGenerator;

public class GeneratorFactory {

	public static Generator create(GeneratorType type) {
		return switch (type) {
			case JAVA -> new JavaGenerator();
		};
	}

}
