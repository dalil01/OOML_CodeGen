package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.models.Node;

import java.util.Map;

public class NConstructor extends Node {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LName.class, NbTime.ZERO_OR_ONE);
		map.put(LAccessModifierConstructor.class, NbTime.ZERO_OR_MULTI);
		map.put(NParameter.class, NbTime.ZERO_OR_MULTI);
	}

}
