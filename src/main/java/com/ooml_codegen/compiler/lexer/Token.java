package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

public class Token {

	private final TokenType type;

	private final Path filePath;
	private final int lineN;
	private final int charN;

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private final Optional<String> value;

	public Token(TokenType type, Path filePath, int lineN, int charN) {
		this.type = type;
		this.value = Optional.empty();
		this.filePath = filePath;
		this.lineN = lineN;
		this.charN = charN;
	}

	public Token(TokenType type, String value, Path filePath, int lineN, int charN) {
		this.type = type;
		this.value = Optional.of(value);
		this.filePath = filePath;
		this.lineN = lineN;
		this.charN = charN;
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

	public Path getFilePath(){
		return this.filePath;
	}

	public int getCharN(){
		return this.charN;
	}

	public int getLineN(){
		return this.lineN;
	}

	@Override
	public String toString() {
		return "Token{" +
				"type=" + this.type +
				", value=" + this.getStringValue() +
				", file=" + this.getFilePath() +
				", line=" + this.getLineN() +
				", char=" + this.getCharN() +
				'}';
	}

	public String getLocation() {
		return this.getFilePath() + "@" + this.getLineN() + ":" + this.getCharN();
	}

}
