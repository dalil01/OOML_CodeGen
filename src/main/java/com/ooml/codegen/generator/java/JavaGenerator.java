package com.ooml.codegen.generator.java;

import com.ooml.codegen.generator.Generator;
import com.ooml.codegen.generator.java.v20.Java20ClassGenerator;
import com.ooml.codegen.models.nodes.*;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.concurrent.atomic.AtomicReference;

public class JavaGenerator extends Generator {

	public JavaGenerator(NClass nClass) {
		super(nClass);
	}

	public JavaGenerator(NEnum nEnum) {
		super(nEnum);
	}

	@Override
	public void generate() {
		if (this.node instanceof NClass) {
			new Java20ClassGenerator((NClass) this.node).generate();
		}
	}

	@Override
	protected void generateComment(LComment lComment) {
		Generator.append(lComment.isMultiLine() ? "/*" : "//");
		Generator.append(lComment.getValue());

		if (lComment.isMultiLine()) {
			Generator.append("*/");
		}
	}

	@Override
	protected void generatePackage(NPackage nPackage) {
		/*
		this.traverse(nPackage, (index, childNode) -> {
			if (childNode instanceof LDeclaration) {
				Generator.append("package");
			} else if (childNode instanceof LName) {
				String formattedName = ((LName) childNode).getValue();
				formattedName = formattedName.replaceAll(" ", ".");
				Generator.append(formattedName);
			}
		});

		Generator.append(";\n");

		 */
	}

	@Override
	protected void generateAccessModifier(LAccessModifier lAccessModifier) {
		LAccessModifier modifier = lAccessModifier.findModifierFromSignValue();

		String value = "";
		if (modifier instanceof LAccessModifier.Public) {
			value = "public";
		}
		else if (modifier instanceof LAccessModifier.Private) {
			value = "private";
		}
		else if (modifier instanceof LAccessModifier.Protected) {
			value = "protected";
		}

		Generator.append(value);
	}

	@Override
	protected void generateNonAccessModifier(LNonAccessModifier lNonAccessModifier) {
		Generator.append(lNonAccessModifier.getValue());
	}

	@Override
	protected void generateDeclaration(LDeclaration lDeclaration) {
		Generator.append(lDeclaration.getValue());
	}

	@Override
	protected void generateName(LName lName) {
		Generator.append(lName.getValue());
	}

	@Override
	protected void generateInheritance(NInheritance nInheritance) {
		/*
		if (nInheritance instanceof NInheritance.NInheritanceClass) {
			Generator.append("extends");
		} else {
			Generator.append("implements");
		}

		Generator.append(" ");

		int size = nInheritance.getChildren().size();
		this.traverse(nInheritance, (index, childNode) -> {
			if (childNode instanceof LValue) {
				this.generateValue((LValue) childNode);
				if (index > 0 && index < size - 1) {
					Generator.append(",");
				}
			}
		});

		 */
	}

	@Override
	protected void generateValue(LValue lValue) {
		Generator.append(lValue.getValue());
	}

	@Override
	protected void generateAttribute(NAttribut nAttribut) {
		/*
		AtomicReference<LType> lType = new AtomicReference<>();
		AtomicReference<LName> lName = new AtomicReference<>();

		this.traverse(nAttribut, (index, childNode) -> {
			if (childNode instanceof LAccessModifier) {
				this.generateAccessModifier((LAccessModifier) childNode);
			}
			else if (childNode instanceof LNonAccessModifier) {
				this.generateNonAccessModifier((LNonAccessModifier) childNode);
			}
			else if (childNode instanceof LName) {
 				lName.set((LName) childNode);
			}
			else if (childNode instanceof LType) {
				lType.set((LType) childNode);
			}
		});

		 */


	}

}
