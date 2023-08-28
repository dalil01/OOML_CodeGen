package com.ooml.codegen.generator;

import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.NAttribut;
import com.ooml.codegen.models.nodes.NInheritance;
import com.ooml.codegen.models.nodes.NPackage;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public abstract class Generator implements IGenerator {

	private static StringBuilder s = new StringBuilder();

	protected final Node node;

	protected Generator(Node node) {
		this.node = node;
	}

	protected void traverse(Node node, Class<?>[] orders, BiConsumer<Integer, Node> action) {


		AtomicInteger i = new AtomicInteger();
		node.getChildren().forEach(childNode -> {
			if (childNode.isOnNewLine()) {
				Generator.append("\n");
			}

			if (childNode instanceof LComment) {
				this.generateComment((LComment) childNode);
			} else {
				action.accept(i.get(), childNode);
			}

			if (childNode.hasNext()) {
				Generator.append(" ");
			}

			i.getAndIncrement();
		});
	}

	public static void main(String[] args) {

	}

	protected static void append(String str) {
		Generator.s.append(str);
	}

	public void printGenerated() {
		System.out.println(s);
	}

	protected abstract void generateComment(LComment lComment);

	protected abstract void generatePackage(NPackage nPackage);

	protected abstract void generateAccessModifier(LAccessModifier lAccessModifier);

	protected abstract void generateNonAccessModifier(LNonAccessModifier lNonAccessModifier);

	protected abstract void generateDeclaration(LDeclaration lDeclaration);

	protected abstract void generateName(LName lName);

	protected abstract void generateInheritance(NInheritance nInheritance);

	protected abstract void generateValue(LValue lValue);

	protected abstract void generateAttribute(NAttribut nAttribut);

}
