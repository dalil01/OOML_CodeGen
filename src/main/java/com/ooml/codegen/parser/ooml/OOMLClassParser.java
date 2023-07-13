package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.lexer.LexerManager;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.Token.TokenType;
import com.ooml.codegen.modelizer.nodes.ClassModelizer;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.UContextStack;
import com.ooml.codegen.utils.ULogger;

import java.util.List;
import java.util.stream.Stream;

public class OOMLClassParser extends Parser {

	private final ClassModelizer classModelizer = new ClassModelizer();

	public OOMLClassParser(LexerManager lexerManager) {
		super(lexerManager);
	}

	@Override
	public Stream<ICodeGenNode> parse() throws Exception {
		this.parsePackage();
		this.parseAccessModifier();
		this.parseNonAccessModifiers();
		this.parseClassDeclaration();
		this.parseClassInheritance();
		this.parseInterfaceInheritance();
		this.parseClassBody();

		this.classModelizer.getModel().printTree();

		return null;
	}

	private void parsePackage() throws Exception {
		Token.TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == Token.TokenType.PACKAGE) {
			// We are sure to have the package name in the following token.
			this.lexerManager.nextTokenType(true);
			this.classModelizer.addPackage(this.lexerManager.consumeTokens());
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

		this.classModelizer.addAccessModifier(this.lexerManager.consumeTokens());
	}

	private void parseNonAccessModifiers() throws Exception {
		TokenType nextTokenType = this.lexerManager.nextTokenType(true);

		while (nextTokenType != TokenType.CLASS) {
			this.classModelizer.addNonAccessModifier(this.lexerManager.consumeTokens());
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

			this.classModelizer.addClassInheritance(tokenList);
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

			this.classModelizer.addInterfaceInheritance(tokenList);
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

		nextTokenType = this.lexerManager.nextTokenType(true);
		while (nextTokenType != TokenType.EOF) {

			nextTokenType = this.lexerManager.nextTokenType(true);
		}
	}

}
