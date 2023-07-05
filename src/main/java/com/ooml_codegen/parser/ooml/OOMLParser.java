package com.ooml_codegen.parser.ooml;

import com.ooml_codegen.generator.ICodeGenNode;
import com.ooml_codegen.lexer.Token;
import com.ooml_codegen.lexer.TokenType;
import com.ooml_codegen.lexer.ooml.OOMLLexerManager;
import com.ooml_codegen.parser.Parser;
import com.ooml_codegen.utils.UContextStack;
import com.ooml_codegen.utils.enums.ContextType;
import com.ooml_codegen.validator.ooml.nodes.OOMLClassValidator;
import com.ooml_codegen.models.Class;
import com.ooml_codegen.utils.ULogger;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

public class OOMLParser extends Parser {

	private final List<Token> packageTokenList = new ArrayList<>();

	private final UContextStack packageUContextStack = new UContextStack();
	private boolean inPackageBlockContext = false;

	private final List<Token> unConsumedTokenList = new ArrayList<>();

	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
	}

	@Override
	public Stream<ICodeGenNode> parse() throws Exception {
		Stream.Builder<ICodeGenNode> streamBuilder = Stream.builder();

		Token token = this.lexerManager.nextToken();
		while (token.getType() != TokenType.EOF){

			switch (token.getType()) {
				case OPENING_CURLY_BRACKET -> {
					if (this.inPackageBlockContext) {
						this.packageUContextStack.push(ContextType.PACKAGE);
					} else {
						// TODO
						ULogger.error("error unexpected token {");
						return null;
					}
				}
				case CLOSING_CURLY_BRACKET -> {
					if (this.packageUContextStack.empty()) {
						// TODO
						ULogger.error("error unexpected token }");
						return null;
					}
					this.packageUContextStack.pop();
					this.inPackageBlockContext = this.packageUContextStack.empty();
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

	private ICodeGenNode parseClass() throws Exception {
		this.handleUnConsumedTokens();

		OOMLClassValidator validator = new OOMLClassValidator(this.lexerManager);
		validator.validate();
		Class clazz = validator.getValidatedClass();

		// TODO : manage package

		return clazz;
	}

}
