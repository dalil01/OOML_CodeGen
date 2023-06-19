package com.ooml_codegen.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UFile {

	public static List<String> findOOMLFilesPath(String mainPath) {
		List<String> list = new ArrayList<>();

		File file = new File(mainPath);

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
				} else if (isOOMLFile(currentFile.getAbsolutePath())) {
					list.add(currentFile.getAbsolutePath());
				}
			}
		}
		else if (isOOMLFile(mainPath)) {
			list.add(file.getAbsolutePath());
		}

		return list;
	}

	public static boolean isOOMLFile(String filePath) {
		File file = new File(filePath);
		return file.exists() && filePath.endsWith(".ooml");
	}

}
