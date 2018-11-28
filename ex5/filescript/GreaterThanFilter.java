package oop.ex5.filescript;

import java.io.File;

public class GreaterThanFilter extends SizeFilter implements Filter {

	public GreaterThanFilter(double num) {
		super(num);
	}

	/**
	 * @param File
	 * @returns true for files greater then double lower bound
	 **/
	public boolean FilterIt(File file) {
		if (file.length() / 1024 > conditionData) {
			return true;
		}
		return false;
	}
}
