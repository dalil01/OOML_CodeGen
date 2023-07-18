package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.IModelizer;

import java.util.List;

public interface IAttributModelizer extends IModelizer {

	void addAttributAccessModifier(List<Token> tokenList);

	void addNonAccessModifiers(List<Token> tokenList);

	void addName(List<Token> tokenList);

	void addType(List<Token> tokenList);

	void addValue(List<Token> tokenList);

}