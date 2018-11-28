package oop.ex5.filescript;

import java.io.*;

public interface Filter {
	/**
	 * 
	 * @param file
	 * @return true if the file is passing the filter, otherwise false.
	 */
	public boolean FilterIt(File file);
}
