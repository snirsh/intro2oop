package oop.ex5.filescript;

import java.io.File;

public class NameEqualsFilter extends NameFilter implements Filter {
	public NameEqualsFilter(String str) {
		super(str);
	}
	/**
	 * @param File
	 * @returns true for files, with the same name as string conditionData.
	 **/
	public boolean FilterIt(File file) {
		return file.getName().equals(conditionData);
	}
}
