package com.ooml.codegen.modelizer;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.all.*;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.leafs.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

class Modelizer implements
		IPackageModelizer,
		IClassModelizer,
		IInheritanceClassModelizer, IInheritanceInterfaceModelizer,
		IAttributModelizer,
		IConstructorModelizer,
		IParameterModelizer,
		IMethodModelizer {

	private final Node model;

	private static int lastTokenLine = 1;
	private static boolean onNewLine;

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
		LComment comment;

		if (token.getType() == Token.TokenType.SINGLE_LINE_COMMENT) {
			comment = new LCommentSingleLine(token.getValue());
		} else {
			comment = new LCommentMultiLine(token.getValue());
		}

		comment.setOnNewLine(onNewLine);

		this.model.addChild(comment);
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
				LDeclaration lDeclaration = new LDeclaration(value);
				lDeclaration.setOnNewLine(onNewLine);
				this.addChild(lDeclaration);
			} else if (type == Token.TokenType.WORD || type == Token.TokenType.QUOTED_WORD) {
				LName lName = new LName(value);
				lName.setOnNewLine(onNewLine);
				this.addChild(lName);
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
			LAccessModifierClass lAccessModifierClass = new LAccessModifierClass(value);
			lAccessModifierClass.setOnNewLine(onNewLine);
			this.addChild(lAccessModifierClass);
		});
	}

	@Override
	public void addAttributAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LAccessModifierAttribut lAccessModifierAttribut = new LAccessModifierAttribut(value);
			this.addChild(lAccessModifierAttribut);
		});
	}

	@Override
	public void addConstructorAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LAccessModifierConstructor lAccessModifierConstructor = new LAccessModifierConstructor(value);
			lAccessModifierConstructor.setOnNewLine(onNewLine);
			this.addChild(lAccessModifierConstructor);
		});
	}

	@Override
	public void addMethodAccessModifier(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LAccessModifierMethod lAccessModifierMethod = new LAccessModifierMethod(value);
			lAccessModifierMethod.setOnNewLine(onNewLine);
			this.addChild(lAccessModifierMethod);
		});
	}

	@Override
	public void addNonAccessModifiers(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LNonAccessModifier lNonAccessModifier = new LNonAccessModifier(value);
			lNonAccessModifier.setOnNewLine(onNewLine);
			this.addChild(lNonAccessModifier);
		});
	}

	@Override
	public void addDeclaration(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LDeclaration lDeclaration = new LDeclaration(value);
			lDeclaration.setOnNewLine(onNewLine);
			this.addChild(lDeclaration);
		});
	}

	@Override
	public void addName(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LName lName = new LName(value);
			lName.setOnNewLine(onNewLine);
			this.addChild(lName);
		});
	}

	@Override
	public void addClassInheritance(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type != TokenType.CLASS_INHERITANCE && type != TokenType.INTERFACE_INHERITANCE && type != TokenType.COMMA) {
				LValue lValue = new LValue(value);
				lValue.setOnNewLine(onNewLine);
				this.addChild(lValue);
			}
		});
	}

	@Override
	public void addClassInheritance(IInheritanceClassModelizer modelizer) throws Exception {
		this.addChild(modelizer.getModel());
	}

	@Override
	public void addInterfaceInheritance(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			if (type != TokenType.INTERFACE_INHERITANCE && type != TokenType.CLASS_INHERITANCE && type != TokenType.COMMA) {
				LValue lValue = new LValue(value);
				lValue.setOnNewLine(onNewLine);
				this.addChild(lValue);
			}
		});
	}

	@Override
	public void addInterfaceInheritance(IInheritanceInterfaceModelizer modelizer) throws Exception {
		this.addChild(modelizer.getModel());
	}

	@Override
	public void addType(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LType lType = new LType(value);
			lType.setOnNewLine(onNewLine);
			this.addChild(lType);
		});
	}

	@Override
	public void addValue(List<Token> tokenList) {
		this.foreachAndConsumeComments(tokenList, (type, value) -> {
			LValue lValue = new LValue(value);
			lValue.setOnNewLine(onNewLine);
			this.addChild(lValue);
		});
	}

	private void foreachAndConsumeComments(List<Token> tokenList, BiConsumer<TokenType, String> action) {
		for (Token token : tokenList) {
			onNewLine = token.getLineN() > lastTokenLine;

			if (token.isComment()) {
				this.addComment(token);
				lastTokenLine = token.getLineN();
				continue;
			}

			action.accept(token.getType(), token.getValue());
			lastTokenLine = token.getLineN();
		}
	}

	private void addChild(Node node) {
		this.model.addChild(node);
	}

}
