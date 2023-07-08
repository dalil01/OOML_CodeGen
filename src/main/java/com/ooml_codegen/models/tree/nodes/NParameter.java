package com.ooml_codegen.models.tree.nodes;

import com.ooml_codegen.models.tree.NbTime;
import com.ooml_codegen.models.tree.Node;
import com.ooml_codegen.models.tree.nodes.leafs.*;

import java.util.Map;

public class NParameter extends Node {

	@Override
	protected Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild() {
		return Map.of(
				LComment.class, NbTime.ZERO_OR_MULTI,
				LName.class, NbTime.ONE,
				LType.class, NbTime.ONE
		);
	}

}
