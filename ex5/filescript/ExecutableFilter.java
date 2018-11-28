package oop.ex5.filescript;

import java.io.File;

public class ExecutableFilter extends BooleanFilter implements Filter {

	public ExecutableFilter(boolean bool) {
		super(bool);
	}

	/**
	 * @param File
	 * @returns true for files which can or cannot be executed, according to
	 *          bool.
	 **/
	public boolean FilterIt(File file) {
		return file.canExecute() == conditionData;
	}
}
