package com.ooml.codegen.generator;

import java.util.Map;

public interface ICodeGenNode {

	Map<GenerationContext, Object> getGenerationContext(GeneratorType type);

	String getFileName();

}
