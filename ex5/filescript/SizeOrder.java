package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;

public class SizeOrder implements Comparator<File> {

	/**
	 * @param File
	 *            , File
	 * @returns comparing the length of the files, if equal, compare abs path.
	 **/
	public int compare(File file1, File file2) {
		long file1Size = file1.length();
		long file2Size = file2.length();
		if (file1Size > file2Size) {
			return 1;
		}
		if (file1Size < file2Size) {
			return -1;
		}
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}

}
