package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.IModelizer;

import java.util.List;

public interface IInterfaceInheritanceMlz extends IModelizer {

	void addInterfaceInheritance(List<Token> tokenList);

}
