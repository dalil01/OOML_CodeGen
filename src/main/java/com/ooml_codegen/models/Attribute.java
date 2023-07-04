package com.ooml_codegen.models;

import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

	private final String name;
	private AttributeAccessModifier accessModifier;
	private List<BehaviorModifier> behaviorModifierList = new ArrayList<>();
	private final Type type;

	public Attribute(String name, Type type) {
		this.name = name;
		this.accessModifier = AttributeAccessModifier.DEFAULT;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public AttributeAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public void setAccessModifier(AttributeAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	public List<BehaviorModifier> getBehaviorModifiers() {
		return this.behaviorModifierList;
	}

	public boolean addBehaviorModifier(BehaviorModifier behaviorModifier) {
		return this.behaviorModifierList.add(behaviorModifier);
	}

	public boolean addBehaviorModifiers(List<BehaviorModifier> behaviorModifiers) {
		return this.behaviorModifierList.addAll(behaviorModifiers);
	}

	public Type getType() {
		return this.type;
	}

}
