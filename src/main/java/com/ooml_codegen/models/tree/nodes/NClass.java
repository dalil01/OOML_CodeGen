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
				LAccessModifier.class, NbTime.ONE,
				NAttribut.class, NbTime.ZERO_OR_ONE,
				NConstructor.class, NbTime.ONE_OR_MULTI,
				NMethod.class, NbTime.ONE_OR_MULTI,
				NClass.class, NbTime.ZERO_OR_MULTI
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

	public static void main(String[] args) {
		NClass nClass = new NClass();

		NAttribut attribut = new NAttribut();
		attribut.addChild(new LCommentSingleLine("ok"));

		nClass.addChild(attribut);

		NConstructor con = new NConstructor();
		con.addChild(new NParameter());

		nClass.addChild(con);


		NConstructor con2 = new NConstructor();
		con2.addChild(new NParameter());

		nClass.addChild(con2);


		nClass.printTree();
	}

}
