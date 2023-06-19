package com.ooml_codegen.compiler.lexer;

import java.util.Optional;

public class Token {

	private final TokenType type;

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private final Optional<String> value;

	public Token(TokenType type) {
		this.type = type;
		this.value = Optional.empty();
	}

	public Token(TokenType type, String value) {
		this.type = type;
		this.value = Optional.of(value);
	}

	public TokenType getType() {
		return this.type;
	}

	public Optional<String> getValue() {
		return this.value;
	}

	public String getStringValue() {
		return this.value.orElse("");
	}

	@Override
	public String toString() {
		return "Token{" +
				"type=" + this.type +
				", value=" + this.getStringValue() +
				'}';
	}

}
