package com.ooml.codegen.models;

import com.ooml.codegen.utils.UString;

import java.util.Map;

public abstract class Leaf extends Node {

	private final String value;

	protected Leaf(String value) {
		this.value = value;
	}

	protected void autoSetNbTimeBySupportedChild(Map<Class<? extends Node>, NbTime> map) {
		// Nothing to do.
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
		return this.value;
	}

	@Override
	public String toString() {
		return super.toString() + " (" + this.getValue() + ")";
	}

}
