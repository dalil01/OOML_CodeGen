package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.IModelizer;

import java.util.List;

public interface IConstructorModelizer extends IModelizer {

	void addConstructorAccessModifier(List<Token> tokenList);

	void addName(List<Token> tokenList);

}
