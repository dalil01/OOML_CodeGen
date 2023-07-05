package com.ooml_codegen.generator;

import com.ooml_codegen.generator.GeneratorType;
import com.ooml_codegen.generator.GenerationContext;

import java.util.Map;

public interface IGeneration {

	Map<GenerationContext, Object> getGenerationContext(GeneratorType type);

	String getFileName();

}
