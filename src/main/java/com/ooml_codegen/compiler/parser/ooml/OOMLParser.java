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

import java.io.FileNotFoundException;
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

		Token token = this.nextToken();
		while (token.getType() != TokenType.EOF){
			//System.out.println(token);

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
				case COLON  -> {
					if (!this.inPackageBlockContext) {
						// TODO
						ULogger.error("error unexpected token :");
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
				case PACKAGE -> this.handlePackageTokens();
				case CLASS -> streamBuilder.add(this.parseClass());
				default -> this.unConsumedTokenList.add(token);
			}

			token = this.nextToken();
		}

		return streamBuilder.build();
	}

	private void handlePackageTokens() throws FileNotFoundException {
		this.packageTokenList.addAll(this.unConsumedTokenList);
		this.unConsumedTokenList.clear();

		this.packageTokenList.add(this.getCurrentToken());

		Token packageNameToken = this.nextToken();
		if (packageNameToken.getType() != TokenType.WORD && packageNameToken.getType() != TokenType.QUOTED_WORD) {
			// TODO : error
			ULogger.error("error package name");
			return;
		}

		this.packageTokenList.add(packageNameToken);

		this.inPackageBlockContext = true;
	}

	private void updateUnConsumedTokenList() {
		this.unConsumedTokenList.addAll(0, this.packageTokenList);
	}

	private IGeneration parseClass() throws Exception {
		this.updateUnConsumedTokenList();

		ClassValidator validator = new ClassValidator(this.getLexerManager(), this.unConsumedTokenList);
		validator.validate();
		Class clazz = validator.getToBeGeneratedClass();
		this.unConsumedTokenList.clear();
		this.insertTokensBefore(validator.getUnConsumedTokenList());

		// TODO : manage package

		return clazz;
	}

}
