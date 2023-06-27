package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.GeneratorType;
import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Class implements IGeneration {

	private Name name;
	private Keyword keyword;
	private Package cPackage;
	private ClassAccessModifier accessModifier;
	private final List<Attribute> attributes = new ArrayList<>();
	private final List<Constructor> constructors = new ArrayList<>();
	private final List<Method> methods = new ArrayList<>();
	private final List<Comment> comments = new ArrayList<>();

	private final List<Object> generationOrder = new ArrayList<>();


	public Class() {
	}


	/**
	 *
	 * TODO : to be remove
	 */
	public Class(Name name, Package cPackage, ClassAccessModifier accessModifier) {
		this.name = name;
		this.cPackage = cPackage;
		this.accessModifier = accessModifier;
	}

	public Name getName() {
		return this.name;
	}

	public void setName(Name name) {
		this.name = name;
		this.generationOrder.add(this.name);
	}

	public void addKeyword() {
		this.generationOrder.add(new Keyword());
	}

	public Package getPackage() {
		return this.cPackage;
	}

	public void setPackage(Package cPackage) {
		this.cPackage = cPackage;
		this.generationOrder.add(this.cPackage);
	}

	public ClassAccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	public void setAccessModifier(ClassAccessModifier accessModifier) {
		this.accessModifier = accessModifier;
		this.generationOrder.add(this.accessModifier);
	}

	public boolean addAttribute(Attribute attribute) {
		return this.attributes.add(attribute);
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public boolean addConstructor(Constructor constructor) {
		return this.constructors.add(constructor);
	}

	public List<Constructor> getConstructors() {
		return this.constructors;
	}

	public boolean addMethod(Method method) {
		return this.methods.add(method);
	}

	public List<Method> getMethods() {
		return this.methods;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
		this.generationOrder.add(comment);
	}

	public List<Object> getGenerationOrder() {
		return this.generationOrder;
	}

	@Override
	public Map<GenerationContext, Object> getGenerationContext(GeneratorType type) {
		return Map.of(
				GenerationContext.PACKAGE, this.cPackage.getName(),
				GenerationContext.CLASS_ACCESS_MODIFIER, (type == GeneratorType.JAVA) ? this.accessModifier.getValueForJava() : this.accessModifier.getValueForOOML(),
				GenerationContext.CLASS_NAME, this.name
		);
	}

	@Override
	public String getFileName() {
		return this.name.getValue();
	}

}
