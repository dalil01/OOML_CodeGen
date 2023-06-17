package com.ooml_codegen.compiler.generator.interfaces;

import com.ooml_codegen.compiler.generator.enums.GenerationContext;

import java.util.Map;

public interface IGeneration {

	Map<GenerationContext, Object> getGenerationContext();

	String getFileName();

}
