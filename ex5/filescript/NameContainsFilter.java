package oop.ex5.filescript;

import java.io.File;

public class NameContainsFilter extends NameFilter implements Filter {

	public NameContainsFilter(String str) {
		super(str);
	}

	/**
	 * @param File
	 * @returns true for files, that their name contains conditionData.
	 **/
	public boolean FilterIt(File file) {
		return file.getName().contains(conditionData);
	}
}
