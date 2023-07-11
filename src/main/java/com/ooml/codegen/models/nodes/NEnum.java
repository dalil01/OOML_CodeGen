package com.ooml.codegen.models.nodes;

import com.ooml.codegen.generator.GenerationContext;
import com.ooml.codegen.generator.GeneratorType;
import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.models.Node;

import java.util.Map;

public class NEnum extends Node implements ICodeGenNode {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		// TODO
	}

	@Override
	public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {
		return null;
	}

	@Override
	public String getFileName() {
		return null;
	}

}
