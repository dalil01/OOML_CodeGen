package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OOMLLexer extends Lexer {

	private TokenType type = null;
	private String value = null;

	private String restOfLastLine = "";

	private final Pattern patternSingleLineComment = Pattern.compile("//[^\n\r]*");
	// comment     = "//" [^\n\r\0]*
	private final Pattern patternMultiLineComment = Pattern.compile("/\\*");

	private final Pattern patternImport = Pattern.compile("^@.*");

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

		while (this.scanner.hasNextLine()) {
			String line = this.scanner.nextLine();

			line = this.restOfLastLine + line;
			this.restOfLastLine = "";

			if (this.onImport(line)
					|| this.onComment(line))
			{
				break;
			}
		}

		if (this.type != null && this.value != null) {
			return new Token(this.type, this.value);
		}

		if (this.scanner.hasNextLine()) {
			this.nextToken();
		}

		if (!this.scanner.hasNextLine()) {
			this.scanner.close();
			System.out.println("Scanner closed!");
		}

		return null;
	}

	private boolean onComment(String line) {
		Matcher matcherSingleLineComment = this.patternSingleLineComment.matcher(line);
		Matcher matcherMultiLineComment = this.patternMultiLineComment.matcher(line);

		boolean singleLineCommentFound = matcherSingleLineComment.find();
		boolean multiLineCommentFound = matcherMultiLineComment.find();

		if (singleLineCommentFound && multiLineCommentFound) {
			int singleLineIndex = matcherSingleLineComment.start();
			int multiLineIndex = matcherMultiLineComment.start();

			if (singleLineIndex < multiLineIndex) {
				this.onSingleLineComment(matcherSingleLineComment, line);
			} else {
				this.onMultiLineComment(line);
			}
		} else if (singleLineCommentFound) {
			this.onSingleLineComment(matcherSingleLineComment, line);
		} else if (multiLineCommentFound) {
			this.onMultiLineComment(line);
		}

		return singleLineCommentFound || multiLineCommentFound;
	}

	private void onSingleLineComment(Matcher matcher, String line) {
		this.type = TokenType.SINGLE_LINE_COMMENT;

		if (line.contains("\n")) {
			this.value = StringUtils.substringBetween(line, OOMLKey.SINGLE_LINE_COMMENT.getValue(), "\n");
			this.restOfLastLine = line.substring(matcher.end()) + "\n";
		} else {
			this.value = StringUtils.substringAfter(line, OOMLKey.SINGLE_LINE_COMMENT.getValue());
			this.restOfLastLine = line.substring(matcher.end());
		}

	}

	private void onMultiLineComment(String line) {
		StringBuilder comment = new StringBuilder();
		comment.append(line);

		boolean commentEnded = line.contains(OOMLKey.MULTI_LINE_COMMENT_END.getValue());
		while (!commentEnded && this.scanner.hasNextLine()) {
			line = this.scanner.nextLine();
			comment.append("\n").append(line);

			commentEnded = line.contains(OOMLKey.MULTI_LINE_COMMENT_END.getValue());
		}

		this.type = TokenType.MULTI_LINE_COMMENT;
		if (commentEnded) {
			this.value = StringUtils.substringBetween(comment.toString(), OOMLKey.MULTI_LINE_COMMENT_START.getValue(), OOMLKey.MULTI_LINE_COMMENT_END.getValue());
			this.restOfLastLine = StringUtils.substringAfter(comment.toString(), this.value + OOMLKey.MULTI_LINE_COMMENT_END.getValue());
		} else {
			if (line.isEmpty()) {
				comment.append('\n');
			}

			//System.out.println(StringUtils.replace(comment.toString(), "\n", "$"));
			this.value = StringUtils.substringAfter(comment.toString(), OOMLKey.MULTI_LINE_COMMENT_START.getValue());
			this.restOfLastLine = "";
		}
	}

	private boolean onImport(String line) {
		line = line.trim();
		Matcher matcher = this.patternImport.matcher(line);
		System.out.println(this.restOfLastLine);

		boolean importFound = matcher.find();

		if (importFound) {
			this.type = TokenType.IMPORT;
/*
			Matcher matcherSingleLineComment = this.patternSingleLineCommentWithSpaceBefore.matcher(line);
			Matcher matcherMultiLineComment = this.patternMultiLineCommentWithSpaceBefore.matcher(line);

			boolean singleLineCommentFound = matcherSingleLineComment.find();
			boolean multiLineCommentFound = matcherMultiLineComment.find();

			if (singleLineCommentFound && multiLineCommentFound) {
				int singleLineIndex = matcherSingleLineComment.start();
				int multiLineIndex = matcherMultiLineComment.start();

				if (singleLineIndex < multiLineIndex) {
					this.value = StringUtils.substringBetween(line, OOMLKey.IMPORT.getValue(), OOMLKey.SINGLE_LINE_COMMENT.getValue());
				} else {
					this.value = StringUtils.substringBetween(line, OOMLKey.IMPORT.getValue(), OOMLKey.MULTI_LINE_COMMENT_START.getValue());
				}
			} else if (singleLineCommentFound) {
				this.value = StringUtils.substringBetween(line, OOMLKey.IMPORT.getValue(), OOMLKey.SINGLE_LINE_COMMENT.getValue());
			} else if (multiLineCommentFound) {
				this.value = StringUtils.substringBetween(line, OOMLKey.IMPORT.getValue(), OOMLKey.MULTI_LINE_COMMENT_START.getValue());
			} else {
				if (line.contains("\n")) {
					this.value = StringUtils.substringBefore(line, "\n");
				} else {
					this.value = StringUtils.substringAfter(line, OOMLKey.IMPORT.getValue());
				}
			}

			this.restOfLastLine = StringUtils.substringAfter(line, this.value) + "\n";*/
		}

		return importFound;
	}

}
