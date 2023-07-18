package com.ooml.codegen.modelizer.all;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.modelizer.IModelizer;

import java.util.List;

public interface IClassModelizer extends IModelizer {

	void addPackage(IPackageModelizer modelizer) throws Exception;

	void addClassAccessModifier(List<Token> tokenList);

	void addNonAccessModifiers(List<Token> tokenList);

	void addDeclaration(List<Token> tokenList);

	void addName(List<Token> tokenList);

	void addClassInheritance(IInheritanceClassModelizer modelizer) throws Exception;

	void addInterfaceInheritance(IInheritanceInterfaceModelizer modelizer) throws Exception;

}
