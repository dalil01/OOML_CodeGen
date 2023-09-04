package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.IModelizer;

import java.util.List;

public interface IInheritanceClassModelizer extends IModelizer {

	void addClassInheritance(List<Token> tokenList);

}
