package com.ooml_codegen.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class UFileTest {

	private final static Path pathPrefix = Path.of(Path.of(System.getProperty("user.dir")).normalize() + "/src/test/java/com/ooml_codegen/utils/ooml/original.ooml");

	@Test
	public void findOOMLFilesPathTest() {
		List<File> emptyDir = UFile.findOOMLFilesPath("empty", pathPrefix.toFile());
		Assertions.assertTrue(emptyDir.isEmpty());

		List<File> invalidOOMLFile = UFile.findOOMLFilesPath("empty/empty.txt", pathPrefix.toFile());
		Assertions.assertTrue(invalidOOMLFile.isEmpty());

		String mainOOMLPath = "main.ooml";
		List<File> foundMainOOML = UFile.findOOMLFilesPath(mainOOMLPath, pathPrefix.toFile());
		Assertions.assertEquals(1, foundMainOOML.size());
		Assertions.assertEquals(pathPrefix.resolveSibling("main.ooml").toString(), foundMainOOML.get(0).toPath().normalize().toString());

		List<File> OOMLFilesInDir = UFile.findOOMLFilesPath("files", pathPrefix.toFile());
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
		Assertions.assertTrue(UFile.isOOMLFile(new File(pathPrefix.getParent() + "/main.ooml")));
		Assertions.assertFalse(UFile.isOOMLFile(new File(pathPrefix.getParent() + "/empty/empty.txt")));
		Assertions.assertFalse(UFile.isOOMLFile(new File(pathPrefix.getParent() + "/main.ooomml")));
		Assertions.assertFalse(UFile.isOOMLFile(new File("babla.ooml")));
	}


}
