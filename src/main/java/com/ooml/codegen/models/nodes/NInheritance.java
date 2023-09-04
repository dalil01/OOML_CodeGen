package com.ooml.codegen.models.nodes;

import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.LValue;

import java.util.Map;

public abstract class NInheritance extends Node {

	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		map.put(LValue.class, NbTime.ONE_OR_MULTI);
	}

	public static class NInheritanceClass extends NInheritance {
	}

	public static class NInheritanceInterface extends NInheritance {
	}

}
