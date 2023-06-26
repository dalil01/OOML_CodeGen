package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

public class OOMLParser extends Parser {

	private Deque<Token> leftTokens;

	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
		this.leftTokens = new ArrayDeque<>();
	}

	@Override
	public void parse() throws FileNotFoundException {
		Token token = this.lexerManager.nextToken();

		while (token.getType() != TokenType.EOF){
			TokenType type = token.getType();
			String value = token.getStringValue();

			System.out.println(token);

			switch (type) {
				case PACKAGE -> this.parsePackage();
				//case ACCESS_MODIFIER_BLOCK -> parseAccessModifierBlock(token);
			}

			token = this.lexerManager.nextToken();
		}
	}
/*
	private void parseAccessModifierBlock(Token token) {
		if (inStructure) {
			this.accessModifierBlocState = token.getStringValue().charAt(0);
		} else {
			ULogger.error("Access Modifier block not in a structure (class/interface/enum/...) at " + token.getLocation());
			// TODO: need to throw a better exception
			throw new RuntimeException();
		}
	}
*/


	private void parsePackage() throws FileNotFoundException {
		Token nextToken = this.lexerManager.nextToken();

		if (nextToken.getType() != TokenType.WORD && nextToken.getType() != TokenType.QUOTED_WORD) {
			ULogger.error("Invalid package statement at " + nextToken.getLocation());
			// TODO: need to throw the error, but need a proper exception class for that
			return;
		}


	}

}
