package com.ooml_codegen.generator.interfaces;

import com.ooml_codegen.generator.enums.GenerationContext;

import java.util.Map;

public interface IGeneration {

	Map<GenerationContext, Object> getGenerationContext();

	String getFileName();

}
