package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;

import java.util.List;

public interface IPackageModelizer extends IClassModelizer {

	void addPackage(List<Token> tokenList);

}
