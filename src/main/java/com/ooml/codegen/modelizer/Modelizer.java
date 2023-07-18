package com.ooml.codegen.modelizer;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.all.*;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.List;
import java.util.function.BiConsumer;

class Modelizer implements
		IPackageModelizer,
		IClassModelizer,
		IClassInheritanceModelizer,
		IInterfaceInheritanceModelizer,
		IAttributModelizer,
		IConstructorModelizer,
		IParameterModelizer,
		IMethodModelizer
{

	private final Node model;

	protected Modelizer(Node model) {
		this.model = model;
	}

	@Override
	public Node getModel() throws Exception {
		this.model.check();
		return this.model;
	}

	@Override
	public void addComment(Token token) {
		if (token.getType() == Token.TokenType.SINGLE_LINE_COMMENT) {
			this.model.addChild(new LCommentSingleLine(token.getValue()));
		} else {
			this.model.addChild(new LCommentMultiLine(token.getValue()));
		}
	}

	@Override
	public void addComments(List<Token> tokenList) {
		for (Token token : tokenList) {
			if (token.isComment()) {
				this.addComment(token);
			}
		}
	}

	@Override
	public void addPackage(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type == Token.TokenType.PACKAGE) {
				this.addChild(new LDeclaration(value));
			} else if (type == Token.TokenType.WORD || type == Token.TokenType.QUOTED_WORD) {
				this.addChild(new LName(value));
			}
		});
	}

	@Override
	public void addPackage(IPackageModelizer modelizer) throws Exception {
		this.addChild(modelizer.getModel());
	}

	@Override
	public void addClassAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierClass(value));
		});
	}

	@Override
	public void addAttributAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierAttribut(value));
		});
	}

	@Override
	public void addConstructorAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierConstructor(value));
		});
	}

	@Override
	public void addMethodAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LAccessModifierMethod(value));
		});
	}

	@Override
	public void addNonAccessModifiers(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LNonAccessModifier(value));
		});
	}

	@Override
	public void addDeclaration(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LDeclaration(value));
		});
	}

	@Override
	public void addName(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LName(value));
		});
	}

	@Override
	public void addClassInheritance(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type != TokenType.INTERFACE_INHERITANCE && type != TokenType.COMMA) {
				this.addChild(new LValue(value));
			}
		});
	}

	@Override
	public void addClassInheritance(IClassInheritanceModelizer modelizer) throws Exception {
		this.addChild(modelizer.getModel());
	}

	@Override
	public void addInterfaceInheritance(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type != TokenType.CLASS_INHERITANCE && type != TokenType.COMMA) {
				this.addChild(new LValue(value));
			}
		});
	}

	@Override
	public void addInterfaceInheritance(IInterfaceInheritanceModelizer modelizer) throws Exception {
		this.addChild(modelizer.getModel());
	}

	@Override
	public void addType(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LType(value));
		});
	}

	@Override
	public void addValue(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			this.addChild(new LValue(value));
		});
	}

	private void foreachAndConsumeComments(List<Token> tokenList, BiConsumer<TokenType, String> action) {
		for (Token token : tokenList) {
			if (token.isComment()) {
				this.addComment(token);
				continue;
			}

			action.accept(token.getType(), token.getValue());
		}
	}

	private void addChild(Node node) {
		this.model.addChild(node);
	}

}
