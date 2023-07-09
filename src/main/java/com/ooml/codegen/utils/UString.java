package com.ooml.codegen.utils;

import java.util.List;

public class UString {

	public static String removeQuotesAround(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}

		List<Character> quotes = List.of('"', '\'', '`');

		if (quotes.contains(s.charAt(0))) {
			s = s.substring(1);
		}

		if (quotes.contains(s.charAt(s.length() - 1))) {
			s = s.substring(0, s.length() - 1);
		}

		return s;
	}

	public static String concatChars(char[] charList) {
		StringBuilder concatenatedString = new StringBuilder();

		for (char ch : charList) {
			concatenatedString.append(ch);
		}

		return concatenatedString.toString();
	}

}
