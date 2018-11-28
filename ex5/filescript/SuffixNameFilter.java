package oop.ex5.filescript;

import java.io.File;

public class SuffixNameFilter extends NameFilter implements Filter {

	public SuffixNameFilter(String str) {
		super(str);
	}

	/**
	 * @param File
	 * @returns true for files, that their name ends with conditionData.
	 **/
	public boolean FilterIt(File file) {
		return file.getName().endsWith(conditionData);
	}

}
