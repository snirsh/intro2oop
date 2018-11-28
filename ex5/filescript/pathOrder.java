package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;

public class pathOrder implements Comparator<File> {

	/**
	 * @param File, File
	 * @returns comparing the abs path.
	 **/
	public int compare(File file1, File file2) {
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}

}
