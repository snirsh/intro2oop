package oop.ex5.filescript;

import java.io.File;

public class AllFilter implements Filter {

	@Override
	/** @param File file
	 * @returns true for all files
	 **/
	public boolean FilterIt(File file) {
		return true;
	}

}
