package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.ModelizerFactory;
import com.ooml.codegen.modelizer.all.IClassInheritanceModelizer;
import com.ooml.codegen.modelizer.all.IClassModelizer;
import com.ooml.codegen.modelizer.all.IInterfaceInheritanceModelizer;
import com.ooml.codegen.modelizer.all.IPackageModelizer;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.UContextStack;
import com.ooml.codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.List;

public class OOMLClassParser extends Parser {

	private final IClassModelizer classModelizer = ModelizerFactory.createClass();

	public OOMLClassParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Node parse() throws Exception {
		this.parsePackage();
		this.parseAccessModifier();
		this.parseNonAccessModifiers();
		this.parseClassDeclaration();
		this.parseClassInheritance();
		this.parseInterfaceInheritance();
		this.parseClassBody();

		return this.classModelizer.getModel();
	}

	private void parsePackage() throws Exception {
		Token.TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == Token.TokenType.PACKAGE) {
			// We are sure to have the package name in the following token.
			this.lexerManager.nextTokenType(true);

			IPackageModelizer packageModelizer = ModelizerFactory.createPackage();
			packageModelizer.addPackage(this.lexerManager.consumeTokens());

			this.classModelizer.addPackage(packageModelizer);
		} else {
			this.lexerManager.restore();
		}
	}

	private void parseAccessModifier() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType != Token.TokenType.SIGN) {
			this.lexerManager.restore();
			return;
		}

		this.classModelizer.addClassAccessModifier(this.lexerManager.consumeTokens());
	}

	private void parseNonAccessModifiers() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		while (nextTokenType != TokenType.CLASS) {
			this.classModelizer.addNonAccessModifiers(this.lexerManager.consumeTokens());
			nextTokenType = this.lexerManager.nextTokenType(true);
		}

		this.lexerManager.restore();
	}

	private void parseClassDeclaration() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		if (nextTokenType != TokenType.CLASS) {
			ULogger.error("Missing currentToken class");
			throw new Exception();
		}

		this.classModelizer.addDeclaration(this.lexerManager.consumeTokens());

		nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.EOF) {
			// TODO
			ULogger.error("Missing classname");
			throw new Exception();
		}

		this.classModelizer.addName(this.lexerManager.consumeTokens());
	}

	private void parseClassInheritance() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.CLASS_INHERITANCE) {

			int i = 1;
			while (nextTokenType != TokenType.EOF) {
				nextTokenType = this.lexerManager.nextTokenType(true);

				if (i % 2 == 0) {
					if (nextTokenType == TokenType.COMMA) {
						i++;
						continue;
					}

					if (nextTokenType == TokenType.INTERFACE_INHERITANCE
							|| nextTokenType == TokenType.OPENING_CURLY_BRACKET
							|| nextTokenType == TokenType.COLON) {
						break;
					}

					ULogger.error("Unexpected token ");
					throw new Exception();
				}

				i++;
			}

			List<Token> tokenList = this.lexerManager.consumeTokens();

			Token lastToken = tokenList.remove(tokenList.size() - 1);
			this.lexerManager.insertTokenBefore(lastToken);

			IClassInheritanceModelizer classInheritanceMlz = ModelizerFactory.createClassInheritance();
			classInheritanceMlz.addClassInheritance(tokenList);

			this.classModelizer.addClassInheritance(classInheritanceMlz);
		}

		this.lexerManager.restore();
	}

	private void parseInterfaceInheritance() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.INTERFACE_INHERITANCE) {
			int i = 1;
			while (nextTokenType != TokenType.EOF) {
				nextTokenType = this.lexerManager.nextTokenType(true);

				if (i % 2 == 0) {
					if (nextTokenType == TokenType.COMMA) {
						i++;
						continue;
					}

					if (nextTokenType == TokenType.OPENING_CURLY_BRACKET
							|| nextTokenType == TokenType.COLON) {
						break;
					}

					ULogger.error("Unexpected token ");
					throw new Exception();
				}

				i++;
			}

			List<Token> tokenList = this.lexerManager.consumeTokens();

			Token lastToken = tokenList.remove(tokenList.size() - 1);
			this.lexerManager.insertTokenBefore(lastToken);

			IInterfaceInheritanceModelizer interfaceInheritanceMlz = ModelizerFactory.createInterfaceInheritance();
			interfaceInheritanceMlz.addInterfaceInheritance(tokenList);

			this.classModelizer.addInterfaceInheritance(interfaceInheritanceMlz);
		}

		this.lexerManager.restore();
	}

	private void parseClassBody() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		UContextStack contextStack = new UContextStack();

		if (nextTokenType == TokenType.OPENING_CURLY_BRACKET) {
			contextStack.push(UContextStack.ContextType.CLASS);
		} else if (nextTokenType != TokenType.COLON) {
			// TODO
			ULogger.error("Missing : or {");
			throw new Exception();
		}

		this.lexerManager.consumeTokens();

		Token accessModifierBlock = null;

		Token nextToken = this.lexerManager.nextToken(true);
		while (nextToken.getType() != TokenType.EOF) {
			switch (nextToken.getType()) {
				case ACCESS_MODIFIER_BLOCK -> accessModifierBlock = nextToken;
				case SIGN -> accessModifierBlock = null;
				case COLON -> {
					this.handleAccessModifierBlock(accessModifierBlock);
					this.classModelizer.getModel().addChild((new OOMLAttributeParser(this.lexerManager)).parse());
				}
				case OPENING_PARENTHESIS -> {
					this.handleAccessModifierBlock(accessModifierBlock);

					while (nextToken.getType() != TokenType.CLOSING_PARENTHESIS) {
						nextToken = this.lexerManager.nextToken(true);
						if (nextToken.getType() == TokenType.EOF) {
							ULogger.error("unexpected token " + nextToken.getValue());
							throw new Exception();
						}
					}

					nextToken = this.lexerManager.nextToken(true);
					this.lexerManager.restore();

					Parser parser = (nextToken.getType() != TokenType.COLON) ? new OOMLConstructorParser(this.lexerManager) : new OOMLMethodParser(this.lexerManager);
					this.classModelizer.getModel().addChild(parser.parse());
				}
				case CLASS -> {
					this.lexerManager.restore();
					this.classModelizer.getModel().addChild((new OOMLClassParser(this.lexerManager)).parse());
				}
				case ENUM -> {
					// TODO
				}
				case INTERFACE -> {
					// TODO
				}
				case CLOSING_CURLY_BRACKET -> {
					if (contextStack.empty()) {
						// TODO
						ULogger.error("unexpected token }");
						throw new Exception();
					}

					contextStack.pop();
					if (contextStack.empty()) {
						return;
					}
				}
				case PACKAGE -> {
					if (contextStack.empty()) {
						return;
					}

					ULogger.error("unexpected token ");
					throw new Exception();
				}
			}

			nextToken = this.lexerManager.nextToken(true);
		}
	}

	private void handleAccessModifierBlock(Token accessModifierBlock) throws FileNotFoundException {
		this.lexerManager.restore();

		if (this.lexerManager.nextToken(true).isAccessModifier()) {
			this.lexerManager.restore();
		} else if (accessModifierBlock != null) {
			this.lexerManager.restore();
			this.lexerManager.insertTokenBefore(accessModifierBlock);
		}
	}

}
