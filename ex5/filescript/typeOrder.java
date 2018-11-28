package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;

public class typeOrder implements Comparator<File> {

	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else
			return "";
	}

	/**
	 * @param File
	 *            , File
	 * @returns comparing the extension of the files, if equal, compare abs path.
	 **/
	public int compare(File file1, File file2) {
		String file1Extansion = getFileExtension(file1);
		String file2Extansion = getFileExtension(file2);
		if (file1Extansion.compareTo(file2Extansion) != 0) {
			return file1Extansion.compareTo(file2Extansion);
		}
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}

}
