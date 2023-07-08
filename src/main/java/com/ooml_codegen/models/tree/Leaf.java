package com.ooml_codegen.models.tree;

import java.util.Arrays;
import java.util.Map;

public abstract class Leaf extends Node {

	private final char[] value;

	public Leaf(String value) {
		this.value = value.toCharArray();
	}

	protected Map<Class<? extends Node>, NbTime> getNbTimeBySupportedChild() {
		return Map.of();
	}

	@Override
	public void addChild(Node node) {
		throw new UnsupportedOperationException("Leaf nodes cannot have children.");
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public boolean hasChild(Node node) {
		return false;
	}

	public String getValue() {
		return Arrays.toString(this.value);
	}

}
