package com.ooml.codegen.modelizer;

import com.ooml.codegen.modelizer.all.*;
import com.ooml.codegen.models.nodes.*;

public class ModelizerFactory {

	public static IPackageMlz createPackage() {
		return new Modelizer(new NPackage());
	}

	public static IClassMlz createClass() {
		return new Modelizer(new NClass());
	}

	public static IClassInheritanceMlz createClassInheritance() {
		return new Modelizer(new NInheritance.NInheritanceClass());
	}

	public static IInterfaceInheritanceMlz createInterfaceInheritance() {
		return new Modelizer(new NInheritance.NInheritanceInterface());
	}

	public static IAttributMlz createAttribut() {
		return new Modelizer(new NAttribut());
	}

	public static IConstructorMlz createConstructor() {
		return new Modelizer(new NConstructor());
	}

	public static IParameterMlz createParameter() {
		return new Modelizer(new NParameter());
	}

	public static IMethodMlz createMethod() {
		return new Modelizer(new NMethod());
	}

}
