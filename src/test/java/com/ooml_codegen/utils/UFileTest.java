package com.ooml_codegen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class UFileTest {

	private final static String pathPrefix = normalizedPath(System.getProperty("user.dir") + "/src/test/java/com/ooml_codegen/utils/ooml/");

	@Test
	public void findOOMLFilesPathTest() {
		List<String> emptyDir = UFile.findOOMLFilesPath(pathPrefix + "empty");
		Assertions.assertTrue(emptyDir.isEmpty());

		List<String> invalidOOMLFile = UFile.findOOMLFilesPath(pathPrefix + "empty/empty.txt");
		Assertions.assertTrue(invalidOOMLFile.isEmpty());

		String mainOOMLPath = pathPrefix + "main.ooml";
		List<String> foundMainOOML = UFile.findOOMLFilesPath(mainOOMLPath);
		Assertions.assertEquals(1, foundMainOOML.size());
		Assertions.assertEquals(mainOOMLPath, normalizedPath(foundMainOOML.get(0)));

		List<String> OOMLFilesInDir = UFile.findOOMLFilesPath(pathPrefix + "files");
		Assertions.assertEquals(4, OOMLFilesInDir.size());
		OOMLFilesInDir = OOMLFilesInDir.stream().map(UFileTest::normalizedPath).collect(Collectors.toList());
		Assertions.assertTrue(OOMLFilesInDir.contains(pathPrefix + "files/main.ooml"));
		String srcPath = pathPrefix + "files/src/";
		Assertions.assertTrue(OOMLFilesInDir.contains(srcPath + "controllers.ooml"));
		Assertions.assertTrue(OOMLFilesInDir.contains(srcPath + "models.ooml"));
		Assertions.assertTrue(OOMLFilesInDir.contains(srcPath + "services.ooml"));
	}

	@Test
	public void isOOMLFileTest() {
		Assertions.assertTrue(UFile.isOOMLFile(pathPrefix + "main.ooml"));
		Assertions.assertFalse(UFile.isOOMLFile(pathPrefix + "empty/empty.txt"));
		Assertions.assertFalse(UFile.isOOMLFile(pathPrefix + "main.ooomml"));
		Assertions.assertFalse(UFile.isOOMLFile("babla.ooml"));
	}

	private static String normalizedPath(String s) {
		return s.replaceAll("\\\\", "/");
	}

}
