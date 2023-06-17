package com.ooml_codegen.models;

import com.ooml_codegen.compiler.generator.enums.GenerationContext;
import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.models.enums.modifiers.access.ClassAccessModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Class implements IGeneration {

	private final String name;
	private final Package cPackage;
	private final ClassAccessModifier accessModifier;
	private final List<Attribute> attributes = new ArrayList<>();
	private final List<Constructor> constructors = new ArrayList<>();
	private final List<Method> methods = new ArrayList<>();


	public Class(String name, Package cPackage, ClassAccessModifier accessModifier) {
		this.name = name;
		this.cPackage = cPackage;
		this.accessModifier = accessModifier;
	}

	public String getName() {
		return this.name;
	}

	public Package getPackage() {
		return this.cPackage;
	}

	public ClassAccessModifier getAccessModifier() {
		return this.accessModifier;
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

	@Override
	public Map<GenerationContext, Object> getGenerationContext() {
		return Map.of(
				GenerationContext.PACKAGE, this.cPackage,
				GenerationContext.CLASS_ACCESS_MODIFIER, this.accessModifier.getValue(),
				GenerationContext.CLASS_NAME, this.name
		);
	}

	@Override
	public String getFileName() {
		return this.name;
	}

}
