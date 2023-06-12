package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

	private TokenType type = null;
	private String value = null;

	private int currentChar = 0;
	private boolean charInUse = false;

	private int peek() {
		if (!this.charInUse) {

			try {
				this.currentChar = this.reader.read();
				//System.out.println((char) this.currentChar);

			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
				this.currentChar = -1;
			}
			this.charInUse = true;
		}
		return this.currentChar;
	}

	private int read() {
		if (this.charInUse) {
			this.charInUse = false;
			return this.currentChar;
		}
		try {
			this.currentChar = this.reader.read();
			//System.out.println((char) this.currentChar);

		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return -1;
		}
		this.charInUse = true;
		return this.currentChar;
	}


	public OOMLLexer(String filePath) {
		super(filePath);
	}

	public Stream<Token> tokenize() throws FileNotFoundException {
		try {
			// TODO : Check file extension, must be .ooml
			File file = new File(this.filePath);
			this.reader = new BufferedReader(new FileReader(file));

			Stream<Token> tokenStream = Stream.generate(this::nextToken).takeWhile(Objects::nonNull);
			tokenStream = Stream.concat(tokenStream, Stream.of(new Token(TokenType.EOF, "")));

			return tokenStream;
		} catch (FileNotFoundException e) {
			System.out.println("Lexer.tokenize: Error while opening file " + filePath + " ; " + e.getMessage());
			throw e;
		}
	}


	private void consumePadding() {
		while (peek() != -1 && OOMLKey.PAD.getValue().indexOf((char) peek()) != -1) {
			read();
		}
	}

	private Token generateCommentToken() {
		if (peek() == '*')
			return generateMultiLineCommentToken();
		return generateSingleLineCommentToken();
	}

	private Token generateSingleLineCommentToken() {
		read();
		StringBuilder s = new StringBuilder();
		while (peek() != -1 && OOMLKey.NEWLINE.getValue().indexOf((char) peek()) == -1){
			s.append((char) read());
		}
		return new Token(TokenType.SINGLE_LINE_COMMENT, s.toString());
	}

	private Token generateMultiLineCommentToken() {
		read();
		StringBuilder s = new StringBuilder();
		while (true) {
			while (peek() != -1 && peek() != '*') {
				s.append((char) read());
			}
			// if we reached EOF (no '*')
			if (read() == -1){
				break;
			}

			// someone added a '*' just before EOF
			if (peek() == -1){
				s.append('*');
				break;
			}

			if (peek() == '/'){
				read();
				break;
			}

			s.append('*');

		}
		return new Token(TokenType.MULTI_LINE_COMMENT, s.toString());
	}

	private Token generateImportToken() {
		return null;
	}


	private Token generateWordToken() {
		return generateWordToken("");
	}

	// Need to implement proper Exception stuff
	private Token generateQuotedWord(){
		int quote = read();
		StringBuilder s = new StringBuilder();
		while (peek() != -1 && peek() != quote){
			if (peek() == '\\'){
				read();
				if (peek() != quote && peek() != '\\'){
					System.err.println("Character '" + (char) peek() + "' did not need to be escaped.");
				}
			}
			s.append((char) read());
		}
		if (peek() == quote){
			read();
		} else {
			System.err.println("Quote closed by end of file.");
		}
		return new Token(TokenType.WORD, s.toString());
	}
	private Token generateWordToken(String prefix){

		StringBuilder s = new StringBuilder(prefix + (char) read());
		while (peek() != -1 && OOMLKey.WORD_END.getValue().indexOf((char) peek()) == -1){
			s.append((char) read());
		}
		return new Token(TokenType.WORD, s.toString());
	}

	// processingChar will still be at the last character, needs to be incremented
	private Token generateToken() {
		if (peek() == -1)
			return null;
		switch (peek()) {
			case '/' -> {
				read();
				if (peek() == -1) {
					return new Token(TokenType.WORD, "/");
				}
				if (peek() != '*' && peek() != '/'){
					return generateWordToken("/");
				}
				return generateCommentToken();
			}
			case '@' -> {
				return generateImportToken();
			}
			case ':' -> {
				read();
				return new Token(TokenType.COLON, null);
			}
			case '=' -> {
				read();
				return new Token(TokenType.EQUAL, null);
			}
			case '+', '#' -> {
				return new Token(TokenType.SIGN, String.valueOf(read()));
			}
			case '-' -> {
				read();
				if (peek() == '>'){
					//TODO Maybe add inherited stuff to this token
					return new Token(TokenType.INHERITANCE, null);
				}
				return new Token(TokenType.SIGN, "-");
			}
			case '"', '\'', '`' -> {
				return generateQuotedWord();
			}
			default -> {
				return generateWordToken();
			}
		}
	}


	private Token nextToken() {
		this.type = null;
		this.value = null;

		// Consuming PADDING
		consumePadding();

		return generateToken();
	}

}
