package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.models.Node;

import java.util.Map;

public class NMethod extends Node {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LAccessModifierMethod.class, NbTime.ONE);
		map.put(LNonAccessModifier.class, NbTime.ZERO_OR_MULTI);
		map.put(LName.class, NbTime.ONE);
		map.put(NParameter.class, NbTime.ZERO_OR_MULTI);
		map.put(LType.class, NbTime.ONE);
	}

}
