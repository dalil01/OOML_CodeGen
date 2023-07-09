package com.ooml.codegen.models;

import com.ooml.codegen.models.nodes.leafs.LCommentMultiLine;
import com.ooml.codegen.models.nodes.leafs.LCommentSingleLine;
import com.ooml.codegen.utils.UString;

import java.util.*;
import java.util.function.Consumer;

public abstract class Node {

	private final LinkedList<Node> children = new LinkedList<>();

	private final Map<Class<? extends Node>, NbTime> nbTimeBySupportedChild = new HashMap<>();
	private final Map<Class<? extends Node>, Integer> nTimeByChildClass = new HashMap<>();

	protected enum NbTime { ZERO_OR_ONE, ONE, ZERO_OR_MULTI, ONE_OR_MULTI }

	protected Node() {
		this.nbTimeBySupportedChild.put(LCommentSingleLine.class, NbTime.ZERO_OR_MULTI);
		this.nbTimeBySupportedChild.put(LCommentMultiLine.class, NbTime.ZERO_OR_MULTI);
		this.autoSetNbTimeBySupportedChild(this.nbTimeBySupportedChild);
	}

	protected abstract void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map);

	public void addChild(Node node) {
		if (!this.isSupportedChild(node)) {
			// TODO : Improve exception
			throw new UnsupportedOperationException("This node cannot have this type of child.");
		}

		Class<? extends Node> nodeClass = node.getClass();

		NbTime nbTime = this.nbTimeBySupportedChild.get(nodeClass);
		if ((nbTime == NbTime.ZERO_OR_ONE || nbTime == NbTime.ONE) && this.nTimeByChildClass.containsKey(nodeClass)) {
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

	public LinkedList<Node> getChildren() {
		return this.children;
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

	public void check() {
		// TODO : NbTime
	}

	public boolean hasDuplicates() {
		Set<Node> visitedNodes = new HashSet<>();
		return hasDuplicatesHelper(this, visitedNodes);
	}

	private boolean hasDuplicatesHelper(Node node, Set<Node> visitedNodes) {
		if (visitedNodes.contains(node)) {
			return true;
		}

		visitedNodes.add(node);

		for (Node child : node.getChildren()) {
			if (hasDuplicatesHelper(child, visitedNodes)) {
				return true;
			}
		}

		return false;
	}

	public void printTree() {
		System.out.println("°");
		printTreeHelper(this, "", true, new HashSet<>());
		System.out.println();
	}

	private void printTreeHelper(Node node, String prefix, boolean isTail, Set<Node> visitedNodes) {
		String arrow = isTail ? "└── " : "├── ";

		String nodeStr = "";
		if (node instanceof Leaf) {
			nodeStr = node.getClass().getSimpleName() + " (" + UString.replaceNewlines(((Leaf) node).getValue()) + ")";
		} else {
			nodeStr = node.toString();
		}

		if (visitedNodes.contains(node)) {
			nodeStr += " (Already Visited)";
		} else {
			visitedNodes.add(node);
		}

		System.out.println(prefix + arrow + nodeStr);

		for (int i = 0; i < node.getChildren().size(); i++) {
			Node child = node.getChildren().get(i);
			boolean isChildTail = (i == node.getChildren().size() - 1);
			String childPrefix = prefix + (isTail ? "    " : "│   ");
			child.printTreeHelper(child, childPrefix, isChildTail, visitedNodes);
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	private boolean isSupportedChild(Node node) {
		return this.nbTimeBySupportedChild.containsKey(node.getClass());
	}

}
