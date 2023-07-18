package com.ooml.codegen.modelizer;

import com.ooml.codegen.modelizer.all.*;
import com.ooml.codegen.models.nodes.*;

public class ModelizerFactory {

	public static IPackageModelizer createPackage() {
		return new Modelizer(new NPackage());
	}

	public static IClassModelizer createClass() {
		return new Modelizer(new NClass());
	}

	public static IClassInheritanceModelizer createClassInheritance() {
		return new Modelizer(new NInheritance.NInheritanceClass());
	}

	public static IInterfaceInheritanceModelizer createInterfaceInheritance() {
		return new Modelizer(new NInheritance.NInheritanceInterface());
	}

	public static IAttributModelizer createAttribut() {
		return new Modelizer(new NAttribut());
	}

	public static IConstructorModelizer createConstructor() {
		return new Modelizer(new NConstructor());
	}

	public static IParameterModelizer createParameter() {
		return new Modelizer(new NParameter());
	}

	public static IMethodModelizer createMethod() {
		return new Modelizer(new NMethod());
	}

}
