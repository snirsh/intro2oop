package oop.ex5.filescript;

import java.io.File;

public class WritableFilter extends BooleanFilter implements Filter {

	public WritableFilter(boolean bool) {
		super(bool);
	}

	/**
	 * @param File
	 * @returns true for files that are writable or not, according to bool
	 **/
	public boolean FilterIt(File file) {
		return file.canWrite() == conditionData;
	}
}
