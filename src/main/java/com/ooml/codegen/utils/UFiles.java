package com.ooml.codegen.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UFiles {

	public static List<File> findOOMLFilesPath(String mainPath, File original) {
		List<File> list = new ArrayList<>();

		File file = new File(mainPath);
		if (!file.isAbsolute()) {
			file = original.toPath().resolveSibling(file.toPath()).normalize().toFile();
		}

		if (!file.exists()) {
			// TODO: throw error ?
			return list;
		}

		if (file.isDirectory()) {
			Stack<File> stack = new Stack<>();
			stack.push(file);

			while (!stack.empty()) {
				File currentFile = stack.pop();

				if (currentFile.isDirectory()) {
					File[] files = currentFile.listFiles();
					if (files != null) {
						for (File f : files) {
							stack.push(f);
						}
					}
				} else if (isOOMLFile(currentFile)) {
					list.add(currentFile);
				}
			}
		}
		else if (isOOMLFile(file)) {
			list.add(file);
		}

		return list;
	}

	public static boolean isOOMLFile(File file) {
		return file.exists() && file.toPath().toString().endsWith(".ooml");
	}

}
