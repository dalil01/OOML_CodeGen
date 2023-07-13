package com.ooml.codegen.lexer;

import java.nio.file.Path;

public class Token {

	public enum TokenType {

		SINGLE_LINE_COMMENT,
		MULTI_LINE_COMMENT,
		IMPORT,
		PACKAGE,
		CLASS,
		ENUM,
		INTERFACE,
		CLASS_INHERITANCE,
		INTERFACE_INHERITANCE,
		COLON,
		SEMI_COLON,
		EQUAL,
		SIGN,
		QUOTED_WORD,
		WORD,
		COMMA,
		OPENING_PARENTHESIS,
		CLOSING_PARENTHESIS,
		OPENING_BRACKET,
		CLOSING_BRACKET,
		OPENING_CURLY_BRACKET,
		CLOSING_CURLY_BRACKET,
		ACCESS_MODIFIER_BLOCK,
		EOF

	}

	private final TokenType type;

	private final Path filePath;
	private final int lineN;
	private final int charN;

	private final String value;

	public Token(TokenType type, Path filePath, int lineN, int charN) {
		this.type = type;
		this.value = "";
		this.filePath = filePath;
		this.lineN = lineN;
		this.charN = charN;
	}

	public Token(TokenType type, String value, Path filePath, int lineN, int charN) {
		this.type = type;
		this.value = value;
		this.filePath = filePath;
		this.lineN = lineN;
		this.charN = charN;
	}

	public TokenType getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	public Path getFilePath() {
		return this.filePath;
	}

	public int getCharN() {
		return this.charN;
	}

	public int getLineN() {
		return this.lineN;
	}

	public boolean isComment() {
		return this.type == TokenType.SINGLE_LINE_COMMENT || this.type == TokenType.MULTI_LINE_COMMENT;
	}

	@Override
	public String toString() {
		return "Token{" +
				"type=" + this.type +
				", value=" + this.getValue() +
				", file=" + this.getFilePath() +
				", line=" + this.getLineN() +
				", char=" + this.getCharN() +
				'}';
	}

	public String getLocation() {
		return this.getFilePath() + "@" + this.getLineN() + ":" + this.getCharN();
	}

}
