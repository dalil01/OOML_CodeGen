package com.ooml.codegen.modelizer.nodes;

import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.Modelizer;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.models.nodes.NInheritance.NInheritanceClass;
import com.ooml.codegen.models.nodes.NInheritance.NInheritanceInterface;
import com.ooml.codegen.models.nodes.leafs.LValue;

import java.util.List;

public abstract class InheritanceModelizer extends Modelizer {


	protected InheritanceModelizer(Node model) {
		super(model);
	}

	public abstract void addInheritance(List<Token> tokenList);

	public static class Class extends InheritanceModelizer {

		public Class() {
			super(new NInheritanceClass());
		}

		@Override
		public void addInheritance(List<Token> tokenList) {
			this.foreachAndConsumeComments(tokenList, (type, value) -> {
				if (type != TokenType.CLASS_INHERITANCE && type != TokenType.COMMA) {
					this.addChild(new LValue(value));
				}
			});
		}

	}

	public static class Interface extends InheritanceModelizer {

		public Interface() {
			super(new NInheritanceInterface());
		}

		@Override
		public void addInheritance(List<Token> tokenList) {
			this.foreachAndConsumeComments(tokenList, (type, value) -> {
				if (type != TokenType.INTERFACE_INHERITANCE && type != TokenType.COMMA) {
					this.addChild(new LValue(value));
				}
			});
		}

	}

}
