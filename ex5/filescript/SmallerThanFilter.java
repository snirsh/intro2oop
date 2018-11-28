package oop.ex5.filescript;

import java.io.File;

public class SmallerThanFilter extends SizeFilter implements Filter {

	public SmallerThanFilter(double num) {
		super(num);
	}

	/**
	 * @param File
	 * @returns true for files smaller then double lower bound
	 **/
	public boolean FilterIt(File file) {
		if (file.length() / 1024 < conditionData) {
			return true;
		}
		return false;
	}

}
