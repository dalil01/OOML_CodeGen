package com.ooml.codegen.generator.java.v20;

import com.ooml.codegen.generator.Generator;
import com.ooml.codegen.generator.java.JavaGenerator;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.*;
import com.ooml.codegen.models.nodes.leafs.LAccessModifierClass;
import com.ooml.codegen.models.nodes.leafs.LDeclaration;
import com.ooml.codegen.models.nodes.leafs.LName;
import com.ooml.codegen.models.nodes.leafs.LNonAccessModifier;

public class Java20ClassGenerator extends JavaGenerator {

	public Java20ClassGenerator(NClass nClass) {
		super(nClass);
	}

	@Override
	public void generate() {
		/*
		this.traverse(this.node, (index, childNode) -> {
			if (childNode instanceof NPackage) {
				this.generatePackage((NPackage) childNode);
			}
			else if (childNode instanceof LAccessModifierClass) {
				this.generateAccessModifier((LAccessModifierClass) childNode);
			}
			else if (childNode instanceof LNonAccessModifier) {
				this.generateNonAccessModifier((LNonAccessModifier) childNode);
			}
			else if (childNode instanceof LDeclaration) {
				this.generateDeclaration((LDeclaration) childNode);
			}
			else if (childNode instanceof LName) {
				this.generateName((LName) childNode);
				this.generateOpeningContext(childNode);
			}
			else if (childNode instanceof NInheritance) {
				this.generateInheritance((NInheritance) childNode);
				this.generateOpeningContext(childNode);
			}
			else if (childNode instanceof NAttribut) {
				this.generateAttribute((NAttribut) childNode);
			}
			else if (childNode instanceof NConstructor) {

			}
		});

		 */
	}

	private void generateOpeningContext(Node currentNode) {
		if (currentNode.getNext() instanceof NInheritance) {
			return;
		}

		Generator.append(" {\n");
	}

	private void generateClosingContext() {
		Generator.append("\n}");
	}

}
