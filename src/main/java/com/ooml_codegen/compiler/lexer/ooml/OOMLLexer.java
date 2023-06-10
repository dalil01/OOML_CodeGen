package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

	private TokenType type = null;
	private String value = null;

	private String restOfLastLine = "";

	public OOMLLexer(String filePath) {
		super(filePath);
	}

	public Stream<Token> tokenize() throws FileNotFoundException {
		try {
			// TODO : Check file extension, must be .ooml
			File file = new File(this.filePath);
			this.scanner = new Scanner(file);

			Stream<Token> tokenStream = Stream.generate(this::nextToken).takeWhile(Objects::nonNull);
			tokenStream = Stream.concat(tokenStream, Stream.of(new Token(TokenType.EOF, "")));

			return tokenStream;
		} catch (FileNotFoundException e) {
			System.out.println("Lexer.tokenize: Error while opening file " + filePath + " ; " + e.getMessage());
			throw e;
		}
	}

	private Token nextToken() {
		this.type = null;
		this.value = null;

		while (!this.restOfLastLine.equals("") || this.scanner.hasNextLine()) {
			System.out.println(this.restOfLastLine);
			String line = this.restOfLastLine + (this.scanner.hasNextLine() ? scanner.nextLine() : "");

			StringBuilder key = new StringBuilder();
			for (int i = 0; i < line.length(); i++) {
				char currentChar = line.charAt(i);

				if (key.toString().equals("") && Character.isWhitespace(currentChar)) {
					continue;
				}

				key.append(currentChar);

				String k = key.toString();
				if (k.startsWith(OOMLKey.MULTI_LINE_COMMENT_START.getValue())) {
					if (this.onMultiLineComment(line)) break;
				}
				else if (k.startsWith(OOMLKey.SINGLE_LINE_COMMENT.getValue())) {
					this.onSingleLineComment(line, k);
				}
				else if (k.startsWith(OOMLKey.IMPORT.getValue())) {
					this.onImport(line, k);
				}

				if (this.type != null && this.value != null) {
					return new Token(this.type, this.value);
				}
			}
		}

		if (this.scanner != null) {
			this.scanner.close();
			System.out.println("Scanner closed!");
		}

		return null;
	}

	private boolean onMultiLineComment(String line) {
		boolean justBreakLoop = false;

		if (!StringUtils.contains(line, OOMLKey.MULTI_LINE_COMMENT_END.getValue())) {
			this.restOfLastLine = line + "\n";
			return true;
		}

		this.type = TokenType.MULTI_LINE_COMMENT;
		this.value = StringUtils.substringBetween(line, OOMLKey.MULTI_LINE_COMMENT_START.getValue(), OOMLKey.MULTI_LINE_COMMENT_END.getValue());
		this.restOfLastLine = StringUtils.substringAfter(line, this.value + OOMLKey.MULTI_LINE_COMMENT_START.getValue());

		return justBreakLoop;
	}

	private void onSingleLineComment(String line, String key) {
		this.type = TokenType.SINGLE_LINE_COMMENT;
		this.value = StringUtils.substringAfter(line, key);
	}

	private void onImport(String line, String key) {
		this.type = TokenType.IMPORT;
		this.value = StringUtils.substringAfter(line, key).trim();
	}

}
