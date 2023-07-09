package com.ooml.codegen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class UFilesTest {

	private final static Path pathPrefix =  Path.of(Path.of(System.getProperty("user.dir")).normalize() + "/src/test/java/com/ooml/codegen/utils/files/");

	@Test
	public void findOOMLFilesPathTest() {
		List<File> emptyDir = UFiles.findOOMLFilesPath("empty", pathPrefix.toFile());
		Assertions.assertTrue(emptyDir.isEmpty());

		List<File> invalidOOMLFile = UFiles.findOOMLFilesPath("files/empty/empty.txt", pathPrefix.toFile());
		Assertions.assertTrue(invalidOOMLFile.isEmpty());

		String mainOOMLPath = "main.ooml";
		List<File> foundMainOOML = UFiles.findOOMLFilesPath(mainOOMLPath, pathPrefix.toFile());
		Assertions.assertEquals(1, foundMainOOML.size());
		Assertions.assertEquals(pathPrefix.resolveSibling("main.ooml").toString(), foundMainOOML.get(0).toPath().normalize().toString());

		List<File> OOMLFilesInDir = UFiles.findOOMLFilesPath("files", pathPrefix.toFile());
		Assertions.assertEquals(4, OOMLFilesInDir.size());
		List<String> FilesInDirString = OOMLFilesInDir.stream().map((file) -> file.toPath().normalize().toString()).toList();
		Assertions.assertTrue(FilesInDirString.contains(pathPrefix.resolveSibling("files/main.ooml").toString()));
		Path srcPath = pathPrefix.resolveSibling("files/src/");
		Assertions.assertTrue(FilesInDirString.contains(srcPath.resolve( "controllers.ooml").toString()));
		Assertions.assertTrue(FilesInDirString.contains(srcPath.resolve("models.ooml").toString()));
		Assertions.assertTrue(FilesInDirString.contains(srcPath.resolve("services.ooml").toString()));
	}

	@Test
	public void isOOMLFileTest() {
		Assertions.assertTrue(UFiles.isOOMLFile(new File(pathPrefix.getParent() + "/main.ooml")));
		Assertions.assertFalse(UFiles.isOOMLFile(new File(pathPrefix.getParent() + "/empty/empty.txt")));
		Assertions.assertFalse(UFiles.isOOMLFile(new File(pathPrefix.getParent() + "/main.ooomml")));
		Assertions.assertFalse(UFiles.isOOMLFile(new File("babla.ooml")));
	}


}
