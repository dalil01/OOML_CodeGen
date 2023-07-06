package com.ooml_codegen.models.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Node {

	private final LinkedList<Node> children = new LinkedList<>();
	private final LinkedList<Node> nextNodes = new LinkedList<>();
	private final Map<Class<? extends Node>, Integer> nbTimeByChild = new HashMap<>();

	protected abstract Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild();

	public void addChild(Node node) {
		if (!this.isSupportedChild(node)) {
			// TODO : Improve exception
			throw new UnsupportedOperationException("This node cannot have this type of child.");
		}

		Class<? extends Node> nodeClass = node.getClass();

		NbTime nbTime = this.getNbTimeBySupportedChild().get(nodeClass);
		if (nbTime == NbTime.ONE && this.nbTimeByChild.containsKey(nodeClass)) {
			throw new UnsupportedOperationException("This node cannot be added more than once.");
		}

		if (this.hasChildren()) {
			Node lastChild = this.children.getLast();
			lastChild.addNext(node);
		}

		this.children.add(node);
		this.nbTimeByChild.put(nodeClass, 1);
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean hasChild(Node node) {
		return this.children.contains(node);
	}

	public Node getNextChild() {
		if (this.hasChildren()) {
			return this.children.removeFirst();
		}

		return null;
	}

	public boolean hasRequiredChildren() {
		// TODO
		return false;
	}

	public void addNext(Node node) {
		if (this.hasNext()) {
			Node lastNext = this.nextNodes.getLast();
			lastNext.addNext(node);
		}

		this.nextNodes.add(node);
	}

	public boolean hasNext() {
		return !this.nextNodes.isEmpty();
	}

	public Node getNext() {
		if (this.hasNext()) {
			return this.nextNodes.removeFirst();
		}

		return null;
	}

	public void traverse(Consumer<Node> action) {
		// TODO
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		this.traverse(node -> {
			// TODO
		});

		return s.toString();
	}

	private boolean isSupportedChild(Node node) {
		return this.getNbTimeBySupportedChild().containsKey(node.getClass());
	}

}
