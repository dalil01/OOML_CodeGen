package com.ooml_codegen.models.tree.nodes;

import com.ooml_codegen.models.tree.NbTime;
import com.ooml_codegen.models.tree.Node;
import com.ooml_codegen.models.tree.nodes.leafs.LAccessModifier;
import com.ooml_codegen.models.tree.nodes.leafs.LComment;
import com.ooml_codegen.models.tree.nodes.leafs.LName;

import java.util.Map;

public class NMethod extends Node {

	@Override
	protected Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild() {
		return Map.of(
				LComment.class, NbTime.ZERO_OR_MULTI,
				LName.class, NbTime.ONE,
				LAccessModifier.class, NbTime.ZERO_OR_MULTI
		);
	}

}
