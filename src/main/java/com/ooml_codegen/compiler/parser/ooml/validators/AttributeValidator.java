package com.ooml_codegen.compiler.parser.ooml.validators;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.parser.ContextStack;
import com.ooml_codegen.compiler.parser.ContextType;
import com.ooml_codegen.models.Attribute;
import com.ooml_codegen.models.BehaviorModifier;
import com.ooml_codegen.models.Package;
import com.ooml_codegen.models.Type;
import com.ooml_codegen.models.comment.Comment;
import com.ooml_codegen.models.enums.modifiers.access.AttributeAccessModifier;
import com.ooml_codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.List;

public class AttributeValidator extends Validator {

	private final Attribute attribute = new Attribute();

	public AttributeValidator(LexerManager lexerManager) {
		super(lexerManager);
	}

	public Attribute getValidatedAttribute() {
		return this.attribute;
	}

	@Override
	public void validate() throws Exception {
		this.validateAccessModifier();
		this.validateBehaviorModifiers();
		this.validateName();
		this.validateType();
		this.validateDefaultValue();

		Token nextToken = this.nextToken();
		if (nextToken.getType() != TokenType.SEMI_COLON) {
			this.insertToken(nextToken);
		}

		if (!attribute.isValid()) {
			// TODO
			ULogger.error("Invalid attribute");
			throw new Exception();
		}
	}

	private void validateAccessModifier() throws Exception {
		Token nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.SIGN) {
			AttributeAccessModifier accessModifier = AttributeAccessModifier.getModifierFromOOMLSign(nextToken.getValue());
			if (accessModifier == null) {
				// TODO
				ULogger.error("Unexpected token");
				throw new Exception();
			}

			this.attribute.setAccessModifier(accessModifier);
		} else {
			this.insertToken(nextToken);
		}
	}

	private void validateBehaviorModifiers() throws Exception {
		List<Token> beforeColonTokenList = new ArrayList<>();

		Token nextToken = this.nextToken();

		while (nextToken.getType() != TokenType.COLON) {
			beforeColonTokenList.add(nextToken);

			nextToken = this.nextToken();
			System.out.println(nextToken);

			if (nextToken.getType() == TokenType.EOF) {
				// TODO :
				ULogger.error("error");
				throw new Exception();
			}
		}

		if (beforeColonTokenList.size() == 0) {
			this.insertToken(nextToken);
			return;
		}

		if (beforeColonTokenList.size() == 1) {
			this.insertToken(beforeColonTokenList.get(0));
			this.insertToken(nextToken);
			return;
		}

		this.insertToken(beforeColonTokenList.get(beforeColonTokenList.size() - 1));
		beforeColonTokenList.remove(beforeColonTokenList.size() - 1);

		this.insertToken(nextToken);

		for (Token token : beforeColonTokenList) {
			this.attribute.addBehaviorModifier(new BehaviorModifier(token.getValue()));
		}
	}

	private void validateName() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() == TokenType.COLON) {
			// TODO
			ULogger.error("Name not found");
			throw new Exception();
		}

		this.attribute.setName(nextToken.getValue());

		if (this.nextToken().getType() != TokenType.COLON) {
			// TODO
			ULogger.error("Unexpected token");
			throw new Exception();
		}
	}

	private void validateType() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() == TokenType.EOF) {
			// TODO
			ULogger.error("Missing type");
			throw new Exception();
		}

		this.attribute.setType(new Type(nextToken.getValue()));
	}

	private void validateDefaultValue() throws Exception {
		Token nextToken = this.nextToken();

		if (nextToken.getType() != TokenType.EQUAL) {
			this.insertToken(nextToken);
			return;
		}

		nextToken = this.nextToken();
		if (nextToken.getType() == TokenType.OPENING_PARENTHESIS) {
			StringBuilder s = new StringBuilder();

			ContextStack internContext = new ContextStack();

			nextToken = this.nextToken();
			while (!internContext.empty() || nextToken.getType() != TokenType.CLOSING_PARENTHESIS) {
				if (nextToken.getType() == TokenType.WORD || nextToken.getType() == TokenType.QUOTED_WORD) {
					s.append(' ');
				}
				s.append(nextToken.getValue());

				if (nextToken.getType() == TokenType.OPENING_PARENTHESIS) {
					internContext.push(ContextType.PARENTHESIS);
				} else if (nextToken.getType() == TokenType.CLOSING_PARENTHESIS) {
					internContext.pop();
				}

				nextToken = this.nextToken();

				if (nextToken.getType() == TokenType.EOF) {
					// TODO
					ULogger.error("Missing )");
					throw new Exception();
				}
			}

			attribute.setValue(s.toString().trim());
		} else {
			attribute.setValue(nextToken.getValue());
		}
	}

	@Override
	protected void addComment(Comment comment) {
		this.attribute.addComment(comment);
	}

	@Override
	protected void addPackage(Package cPackage) throws Exception {
		ULogger.error("Invalid syntax");
		throw new Exception();
	}

}
