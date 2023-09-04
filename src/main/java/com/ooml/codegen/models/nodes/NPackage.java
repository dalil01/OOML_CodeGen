package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.Map;

public class NPackage extends Node {

	@Override
	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LDeclaration.class, NbTime.ONE);
		map.put(LName.class, NbTime.ONE);
	}

}
