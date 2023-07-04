package com.ooml_codegen.models;

import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

	private AttributeAccessModifier accessModifier = AttributeAccessModifier.DEFAULT;
	private final List<BehaviorModifier> behaviorModifierList = new ArrayList<>();
	private String name;
	private Type type;
	private String value = "";

	private final List<Object> generationOrder = new ArrayList<>();

	public Attribute() {
	}

	// TODO to be remove
	public Attribute(String name, Type type) {
		this.name = name;
		this.type = type;
		this.value = "";
	}

	public AttributeAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public void setAccessModifier(AttributeAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
		this.generationOrder.add(accessModifier);
	}

	public List<BehaviorModifier> getBehaviorModifiers() {
		return this.behaviorModifierList;
	}

	public boolean addBehaviorModifier(BehaviorModifier behaviorModifier) {
		this.generationOrder.add(behaviorModifier);
		return this.behaviorModifierList.add(behaviorModifier);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.generationOrder.add(name);
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
		this.generationOrder.add(type);
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
		this.generationOrder.add(value);
	}

	public void addComment(Comment comment) {
		this.generationOrder.add(comment);
	}

	public boolean isValid() {
		return this.name != null && this.type != null;
	}

}
