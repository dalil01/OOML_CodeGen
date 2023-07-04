package com.ooml_codegen.compiler.parser.ooml;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.compiler.parser.Parser;
import com.ooml_codegen.compiler.parser.ContextStack;
import com.ooml_codegen.compiler.parser.ContextType;
import com.ooml_codegen.compiler.parser.ooml.validators.ClassValidator;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

public class OOMLParser extends Parser {

	private final List<Token> packageTokenList = new ArrayList<>();

	private final ContextStack packageContextStack = new ContextStack();
	private boolean inPackageBlockContext = false;

	private final List<Token> unConsumedTokenList = new ArrayList<>();

	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
	}

	@Override
	public Stream<IGeneration> parse() throws Exception {
		Stream.Builder<IGeneration> streamBuilder = Stream.builder();

		Token token = this.lexerManager.nextToken();
		while (token.getType() != TokenType.EOF){

			switch (token.getType()) {
				case OPENING_CURLY_BRACKET -> {
					if (this.inPackageBlockContext) {
						this.packageContextStack.push(ContextType.PACKAGE);
					} else {
						// TODO
						ULogger.error("error unexpected token {");
						return null;
					}
				}
				case CLOSING_CURLY_BRACKET -> {
					if (this.packageContextStack.empty()) {
						// TODO
						ULogger.error("error unexpected token }");
						return null;
					}
					this.packageContextStack.pop();
					this.inPackageBlockContext = this.packageContextStack.empty();
				}
				case PACKAGE -> {
					this.lexerManager.insertToken(token);
					this.handlePackageTokens();
				}
				case CLASS -> {
					this.unConsumedTokenList.add(token);
					streamBuilder.add(this.parseClass());
				}
				default -> this.unConsumedTokenList.add(token);
			}

			token = this.lexerManager.nextToken();
		}

		return streamBuilder.build();
	}

	private void handlePackageTokens() throws Exception {
		this.packageTokenList.addAll(this.unConsumedTokenList);
		this.unConsumedTokenList.clear();

		this.packageTokenList.add(this.lexerManager.nextToken());

		Token packageNameToken = this.lexerManager.nextToken();
		if (packageNameToken.getType() != TokenType.WORD && packageNameToken.getType() != TokenType.QUOTED_WORD) {
			// TODO : error
			ULogger.error("error package name");
			return;
		}

		this.packageTokenList.add(packageNameToken);

		Token nextToken = this.lexerManager.nextToken();
		if (nextToken.getType() != TokenType.COLON) {
			this.unConsumedTokenList.add(nextToken);
		}

		this.inPackageBlockContext = true;
	}

	private void handleUnConsumedTokens() {
		this.unConsumedTokenList.addAll(0, this.packageTokenList);

		for (Token token : this.unConsumedTokenList) {
			this.lexerManager.insertToken(token);
		}

		this.unConsumedTokenList.clear();
	}

	private IGeneration parseClass() throws Exception {
		this.handleUnConsumedTokens();

		ClassValidator validator = new ClassValidator(this.lexerManager);
		validator.validate();
		Class clazz = validator.getValidatedClass();

		// TODO : manage package

		return clazz;
	}

}
