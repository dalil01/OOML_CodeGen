package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.compiler.parser.TokenContextStack;
import com.ooml_codegen.compiler.parser.TokenContextType;
import com.ooml_codegen.compiler.parser.ooml.validators.ClassValidator;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.utils.ULogger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

public class OOMLParser extends Parser {

	private final List<Token> packageTokenList = new ArrayList<>();
	private final TokenContextStack tokenContextStack = new TokenContextStack();
	private final List<Token> unConsumedTokenList = new ArrayList<>();

	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
	}

	@Override
	public Stream<IGeneration> parse() throws Exception {
		Token token = this.lexerManager.nextToken();

		Stream.Builder<IGeneration> streamBuilder = Stream.builder();

		while (token.getType() != TokenType.EOF){
			//System.out.println(token);

			switch (token.getType()) {
				case CLOSING_CURLY_BRACKET -> {
					if (this.tokenContextStack.empty()) {
						// TODO
						ULogger.error("error unexpected token }");
						return null;
					}
					this.tokenContextStack.pop();
				}
				case PACKAGE -> this.handlePackageTokens();
				case CLASS -> streamBuilder.add(this.parseClass());
				default -> this.unConsumedTokenList.add(token);
			}

			token = this.lexerManager.nextToken();
		}

		return streamBuilder.build();
	}

	private void handlePackageTokens() throws FileNotFoundException {
		this.packageTokenList.addAll(this.unConsumedTokenList);
		this.unConsumedTokenList.clear();

		this.packageTokenList.add(this.lexerManager.getCurrentToken());

		Token packageNameToken = this.lexerManager.nextToken();
		if (packageNameToken.getType() != TokenType.WORD && packageNameToken.getType() != TokenType.QUOTED_WORD) {
			// TODO : error
			ULogger.error("error package name");
			return;
		}

		Token openingCurlyBracketToken = this.lexerManager.nextToken();
		if (openingCurlyBracketToken.getType() != TokenType.OPENING_CURLY_BRACKET) {
			// TODO : error missing {
			ULogger.error("error missing {");
			return;
		}

		this.packageTokenList.add(packageNameToken);

		this.tokenContextStack.push(TokenContextType.PACKAGE);
	}

	private void updateUnConsumedTokenList() {
		this.unConsumedTokenList.addAll(0, this.packageTokenList);
	}

	private IGeneration parseClass() throws Exception {
		this.updateUnConsumedTokenList();

		ClassValidator validator = new ClassValidator(this.lexerManager, this.unConsumedTokenList);
		validator.validate();
		Class clazz = validator.getToBeGeneratedClass();
		this.unConsumedTokenList.clear();

		// TODO : manage package

		return clazz;
	}

}
