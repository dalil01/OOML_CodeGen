package com.ooml.codegen.models.nodes;

import com.ooml.codegen.generator.GenerationContext;
import com.ooml.codegen.generator.GeneratorType;
import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.Map;

public class NClass extends Node implements ICodeGenNode {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(NPackage.class, NbTime.ONE);
		map.put(LName.class, NbTime.ONE);
		map.put(LAccessModifierClass.class, NbTime.ONE);
		map.put(LNonAccessModifier.class, NbTime.ZERO_OR_MULTI);
		map.put(LDeclaration.class, NbTime.ONE);
		map.put(NInheritance.NInheritanceClass.class, NbTime.ZERO_OR_MULTI);
		map.put(NInheritance.NInheritanceInterface.class, NbTime.ZERO_OR_MULTI);
		map.put(NAttribut.class, NbTime.ZERO_OR_MULTI);
		map.put(NConstructor.class, NbTime.ONE_OR_MULTI);
		map.put(NMethod.class, NbTime.ONE_OR_MULTI);
		map.put(NClass.class, NbTime.ZERO_OR_MULTI);
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
