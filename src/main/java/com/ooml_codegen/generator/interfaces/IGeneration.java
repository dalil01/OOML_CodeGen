package com.ooml_codegen.generator.interfaces;

import com.ooml_codegen.generator.enums.GenerationContext;

import java.util.Map;

public interface IGeneration {

	public Map<GenerationContext, Object> toGenerationContext();

}
