package com.ooml_codegen.models.tree.nodes;

import com.ooml_codegen.generator.GenerationContext;
import com.ooml_codegen.generator.GeneratorType;
import com.ooml_codegen.generator.ICodeGenNode;
import com.ooml_codegen.models.tree.NbTime;
import com.ooml_codegen.models.tree.Node;
import com.ooml_codegen.models.tree.nodes.leafs.*;

import java.util.Map;

public class NClass extends Node implements ICodeGenNode {

	@Override
	protected Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild() {
		return Map.of(
			LComment.class, NbTime.ZERO_OR_MULTI,
			LPackage.class, NbTime.ONE,
			LName.class, NbTime.ONE,
			/*
			LAccessModifierDefault.class, NbTime.ONE,
			LAccessModifierPublic.class,
			LAccessModifierPrivate.class,

			 */
			LNonAccessModifier.class, NbTime.ZERO_OR_MULTI
		);
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
