package com.ooml_codegen.compiler.generator.interfaces;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.models.comment.Comment;

import java.util.Map;

public interface IGeneration {

	Map<GenerationContext, Object> getGenerationContext(GeneratorType type);

	String getFileName();

}
