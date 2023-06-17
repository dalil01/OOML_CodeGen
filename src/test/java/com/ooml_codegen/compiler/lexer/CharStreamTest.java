package com.ooml_codegen.compiler.lexer;

import org.junit.jupiter.api.*;

import java.io.*;

public class CharStreamTest {

	private static File file = null;
	private static CharStream charStream = null;

	@BeforeEach
	public void beforeEach() throws IOException {
		file = File.createTempFile("test", ".ooml");
		charStream = new CharStream(file);
	}

	@AfterEach
	public void afterEach() {
		if (!file.delete()) {
			System.err.println("Failed to delete the temporary file: " + file.getAbsolutePath());
		}

		charStream.close();
	}

	@Test
	public void nextTest() throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write("Hello, World!");
		writer.close();

		CharStream charStream = new CharStream(file);

		Assertions.assertEquals('H', charStream.getCurrentChar());

		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('e', charStream.getCurrentChar());

		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('l', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('l', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('o', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals(',', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals(' ', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('W', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('o', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('r', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('l', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('d', charStream.getCurrentChar());
		Assertions.assertTrue(charStream.next());
		Assertions.assertEquals('!', charStream.getCurrentChar());

		Assertions.assertFalse(charStream.next());
		Assertions.assertTrue(charStream.isEOF());

		charStream.close();
	}

	@Test
	public void isEOFTest() {
		Assertions.assertTrue(charStream.isEOF());
	}

	@Test
	public void getCharTest() throws IOException {
		File file = File.createTempFile("test", ".txt");
		FileWriter writer = new FileWriter(file);
		writer.write("OOML");
		writer.close();

		CharStream charStream = new CharStream(file);

		Assertions.assertEquals('O', charStream.getCurrentChar());
		charStream.next();
		Assertions.assertEquals('O', charStream.getCurrentChar());
		charStream.next();
		Assertions.assertEquals('M', charStream.getCurrentChar());
		charStream.next();
		Assertions.assertEquals('L', charStream.getCurrentChar());

		Assertions.assertFalse(charStream.next());
	}

	@Test
	public void closeTest() {
		charStream.close();
		Assertions.assertTrue(charStream.isEOF());
	}

}