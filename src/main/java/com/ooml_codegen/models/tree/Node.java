package com.ooml_codegen.models.tree;

import java.util.*;
import java.util.function.Consumer;

public abstract class Node {

	private final LinkedList<Node> children = new LinkedList<>();
	private final Map<Class<? extends Node>, Integer> nTimeByChildClass = new HashMap<>();

	protected abstract Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild();

	public void addChild(Node node) {
		if (!this.isSupportedChild(node)) {
			// TODO : Improve exception
			throw new UnsupportedOperationException("This node cannot have this type of child.");
		}

		Class<? extends Node> nodeClass = node.getClass();

		NbTime nbTime = this.getNbTimeBySupportedChild().get(nodeClass);
		if (nbTime == NbTime.ONE && this.nTimeByChildClass.containsKey(nodeClass)) {
			throw new UnsupportedOperationException("This node cannot be added more than once.");
		}

		this.children.add(node);

		Integer nTime = this.nTimeByChildClass.get(nodeClass);
		if (nTime == null) {
			nTime = 1;
		}

		this.nTimeByChildClass.put(nodeClass, nTime + 1);
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean hasChild(Node node) {
		return this.children.contains(node);
	}

	public Integer findNTimeChildClass(Class<? extends Node> nodeClass) {
		Integer nTime = this.nTimeByChildClass.get(nodeClass);
		if (nTime == null) {
			return 0;
		}

		return nTime;
	}

	public void traverse(Consumer<Node> action) {
		this.children.forEach((node) -> {
			action.accept(node);

			if (!(node instanceof Leaf)) {
				node.traverse(action);
			}
		});
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public void printTree() {
		System.out.println("°");
		printTreeHelper("", true, new HashSet<>());
	}

	private void printTreeHelper(String prefix, boolean isTail, Set<Node> visitedNodes) {
		String arrow = isTail ? "└── " : "├── ";
		System.out.println(prefix + arrow + this);

		visitedNodes.add(this);

		for (int i = 0; i < this.children.size(); i++) {
			Node child = this.children.get(i);
			String childPrefix = prefix + (isTail ? "    " : "│   ");

			if (!visitedNodes.contains(child)) {
				boolean isChildTail = (i == this.children.size() - 1);
				child.printTreeHelper(childPrefix, isChildTail, visitedNodes);
			} else {
				System.out.println(childPrefix + "├── " + child + " (Already Visited)");
			}
		}
	}

	private boolean isSupportedChild(Node node) {
		return this.getNbTimeBySupportedChild().containsKey(node.getClass());
	}

}
