package com.ooml.codegen.modelizer;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.models.Node;

import java.util.List;

public interface IModelizer {

	Node getModel() throws Exception;
	void addComment(Token token);
	void addComments(List<Token> tokenList);

}
