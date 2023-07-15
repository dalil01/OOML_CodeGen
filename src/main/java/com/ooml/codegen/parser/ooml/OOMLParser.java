package com.ooml.codegen.parser.ooml;

import com.ooml.codegen.generator.ICodeGenNode;
import com.ooml.codegen.lexer.Token;
import com.ooml.codegen.lexer.ooml.OOMLLexerManager;
import com.ooml.codegen.models.Node;
import com.ooml.codegen.utils.UContextStack;
import com.ooml.codegen.models.nodes.NClass;
import com.ooml.codegen.parser.Parser;
import com.ooml.codegen.utils.ULogger;
import com.ooml.codegen.validator.ooml.nodes.OOMLClassValidator;
import com.ooml.codegen.lexer.Token.TokenType;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

public class OOMLParser extends Parser {

	private final List<Token> packageTokenList = new ArrayList<>();

	private final UContextStack packageUContextStack = new UContextStack();

	public OOMLParser(OOMLLexerManager lexer) {
		super(lexer);
	}

	@Override
	public Node parse() throws Exception {
		Stream.Builder<ICodeGenNode> streamBuilder = Stream.builder();

		TokenType nextTokenType = this.lexerManager.nextTokenType();
		while (nextTokenType != TokenType.EOF) {

			switch (nextTokenType) {
				case CLOSING_CURLY_BRACKET -> {
					if (this.packageUContextStack.empty()) {
						// TODO
						ULogger.error("error unexpected token }");
						return null;
					}

					this.packageUContextStack.pop();
					this.packageTokenList.clear();
				}
				case PACKAGE -> {
					this.handlePackageTokens();
				}
				case CLASS -> {
					this.lexerManager.restore();
					this.parseClass();
					//streamBuilder.add();
				}
			}

			nextTokenType = this.lexerManager.nextTokenType();
		}

		return null;
	}

	private void handlePackageTokens() throws Exception {
		this.packageTokenList.addAll(this.lexerManager.consumeTokens());

		TokenType nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType != TokenType.WORD && nextTokenType != TokenType.QUOTED_WORD) {
			// TODO : error
			ULogger.error("error package name");
			return;
		}

		this.packageTokenList.addAll(this.lexerManager.consumeTokens());

		nextTokenType = this.lexerManager.nextTokenType(true);
		if (nextTokenType == TokenType.OPENING_CURLY_BRACKET) {
			this.packageUContextStack.push(UContextStack.ContextType.PACKAGE);
		} else if (nextTokenType != TokenType.COLON) {
			// TODO : error
			ULogger.error("Missing : or {");
		}

		this.lexerManager.consumeTokens();
	}

	private Stream<ICodeGenNode> parseClass() throws Exception {
		this.lexerManager.insertTokensBefore(packageTokenList);

		OOMLClassParser parser = new OOMLClassParser(this.lexerManager);


		parser.parse();

		return null;
	}

}
