package oop.ex5.filescript;

import java.io.File;

public class HiddenFilter extends BooleanFilter implements Filter {

	public HiddenFilter(boolean bool) {
		super(bool);
	}

	/**
	 * @param File
	 * @returns true for files that are hidden or not, according to bool
	 **/
	public boolean FilterIt(File file) {
		return file.isHidden() == conditionData;
	}
}
