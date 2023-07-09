package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.NbTime;
import com.ooml.codegen.models.nodes.leafs.*;
import com.ooml.codegen.models.Node;

import java.util.Map;

public class NMethod extends Node {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LAccessModifierMethod.class, NbTime.ONE);
		map.put(LName.class, NbTime.ONE);
	}

}
