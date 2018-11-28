package oop.ex5.filescript;

import java.io.File;

public class PrefixNameFilter extends NameFilter {

	public PrefixNameFilter(String str) {
		super(str);
	}

	/**
	 * @param File
	 * @returns true for files, that their name starts with conditionData.
	 **/
	public boolean FilterIt(File file) {
		return file.getName().startsWith(conditionData);
	}
}
