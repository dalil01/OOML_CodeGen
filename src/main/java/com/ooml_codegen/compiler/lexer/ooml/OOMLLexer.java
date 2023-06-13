package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

	private String restOfLastLine = "";

	public OOMLLexer(String filePath) {
		super(filePath);
	}

	public Stream<Token> tokenize() throws FileNotFoundException {
		try {
			// TODO : Check file extension, must be .ooml
			File file = new File(this.filePath);
			this.scanner = new Scanner(file);

			return Stream.generate(this::nextToken).takeWhile(Token -> Token.type() != TokenType.EOF);
		} catch (FileNotFoundException e) {
			System.out.println("Lexer.tokenize: Error while opening file " + filePath + " ; " + e.getMessage());
			throw e;
		}
	}

	private Token nextToken() {
		TokenType type = null;
		String value = null;

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

				if (key.toString().startsWith(OOMLKey.MULTI_LINE_COMMENT_START.getValue())) {
					if (!StringUtils.contains(line, OOMLKey.MULTI_LINE_COMMENT_END.getValue())) {
						this.restOfLastLine = line + "\n";
						break;
					}

					type = TokenType.MULTI_LINE_COMMENT;
					value = StringUtils.substringBetween(line, OOMLKey.MULTI_LINE_COMMENT_START.getValue(), OOMLKey.MULTI_LINE_COMMENT_END.getValue());

					this.restOfLastLine = StringUtils.substringAfter(line, value + OOMLKey.MULTI_LINE_COMMENT_START.getValue());
				} else if (key.toString().startsWith(OOMLKey.SINGLE_LINE_COMMENT.getValue())) {
					type = TokenType.SINGLE_LINE_COMMENT;
					value = StringUtils.substringAfter(line, key.toString());
					return new Token(type, value);
				}

				if (type != null && value != null) {
					return new Token(type, value);
				}
			}
		}

		if (this.scanner != null) {
			this.scanner.close();
			System.out.println("Scanner closed!");
		}

		return new Token(TokenType.EOF, "");
	}

}
