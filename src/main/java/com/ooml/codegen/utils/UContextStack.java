package com.ooml.codegen.utils;

import java.util.Stack;

public class UContextStack extends Stack<UContextStack.ContextType> {

	public enum ContextType {

		PACKAGE,
		CLASS,
		ENUM,
		INTERFACE,
		PARENTHESIS

	}

}
