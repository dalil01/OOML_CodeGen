package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.models.Node;

import java.util.Map;

public class NParameter extends Node {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LNonAccessModifier.class, NbTime.ZERO_OR_MULTI);
		map.put(LName.class, NbTime.ONE);
		map.put(LType.class, NbTime.ONE);
	}

}
