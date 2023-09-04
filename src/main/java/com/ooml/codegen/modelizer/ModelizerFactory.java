package com.ooml.codegen.modelizer;

import com.ooml.codegen.modelizer.all.*;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.*;

public class ModelizerFactory {

	public static IPackageModelizer createPackage() {
		return newModelizer(new NPackage());
	}

	public static IClassModelizer createClass() {
		return newModelizer(new NClass());
	}

	public static IInheritanceClassModelizer createClassInheritance() {
		return newModelizer(new NInheritance.NInheritanceClass());
	}

	public static IInheritanceInterfaceModelizer createInterfaceInheritance() {
		return newModelizer(new NInheritance.NInheritanceInterface());
	}

	public static IAttributModelizer createAttribut() {
		return newModelizer(new NAttribut());
	}

	public static IConstructorModelizer createConstructor() {
		return newModelizer(new NConstructor());
	}

	public static IParameterModelizer createParameter() {
		return newModelizer(new NParameter());
	}

	public static IMethodModelizer createMethod() {
		return newModelizer(new NMethod());
	}

	private static Modelizer newModelizer(Node node) {
		return new Modelizer(node);
	}

}
