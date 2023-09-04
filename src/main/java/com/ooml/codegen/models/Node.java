package com.ooml.codegen.models;

import com.ooml.codegen.models.nodes.leafs.LComment;
import com.ooml.codegen.models.nodes.leafs.LCommentMultiLine;
import com.ooml.codegen.models.nodes.leafs.LCommentSingleLine;
import com.ooml.codegen.utils.UString;

import java.util.*;
import java.util.function.Consumer;

public abstract class Node {

	private Node previous;
	private Node next;

	private final LinkedList<Node> children = new LinkedList<>();

	protected enum NbTime { ZERO_OR_ONE, ONE, ZERO_OR_MULTI, ONE_OR_MULTI }

	private final Map<Class<? extends Node>, NbTime> nbTimeBySupportedChild = new HashMap<>();
	private final Map<Class<? extends Node>, Integer> nTimeByChildClass = new HashMap<>();

	private boolean onNewLine = false;

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

		if (this.children.size() > 0) {
			Node lastChild = this.children.getLast();
			node.setPrevious(lastChild);
			lastChild.setNext(node);
		}

		this.children.add(node);

		Integer nTime = this.nTimeByChildClass.get(nodeClass);
		if (nTime == null) {
			nTime = 1;
		}

		this.nTimeByChildClass.put(nodeClass, nTime + 1);
	}

	public boolean hasPrevious() {
		return this.previous != null;
	}

	public Node getPrevious() {
		return this.previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public boolean previousIsComment() {
		return this.previous != null && this.previous instanceof LComment;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	public Node getNext() {
		return this.next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public boolean nextIsComment() {
		return this.next != null && this.next instanceof LComment;
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

	public boolean isOnNewLine() {
		return this.onNewLine;
	}

	public void setOnNewLine(boolean value) {
		this.onNewLine = value;
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

	public void n1Traverse(Consumer<Node> action) {
		this.children.forEach(action);
	}

	public void check() throws Exception {
		// TODO : NbTime
	}

	public boolean hasDuplicates() {
		Set<Node> visitedNodes = new HashSet<>();
		return hasDuplicates(this, visitedNodes);
	}

	private boolean hasDuplicates(Node node, Set<Node> visitedNodes) {
		if (visitedNodes.contains(node)) {
			return true;
		}

		visitedNodes.add(node);

		for (Node child : node.getChildren()) {
			if (hasDuplicates(child, visitedNodes)) {
				return true;
			}
		}

		return false;
	}

	public void printTree() {
		System.out.println("°");
		printTree(this, "", true, new HashSet<>());
		System.out.println();
	}

	private void printTree(Node node, String prefix, boolean isTail, Set<Node> visitedNodes) {
		String arrow = isTail ? "└── " : "├── ";

		String nodeStr;
		if (node instanceof Leaf) {
			nodeStr = node.getClass().getSimpleName() + " (" + UString.replaceNewlines(((Leaf) node).getValue()) + ")" + ((node.isOnNewLine()) ? " +1 " : "");
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
			child.printTree(child, childPrefix, isChildTail, visitedNodes);
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
