package oop.ex5.filescript;

import java.io.File;
/** 
 *  class of all name filters
 **/
public class NameFilter implements Filter {
	String conditionData;

	public NameFilter(String str) {
		conditionData = str;
	}

	@Override
	public boolean FilterIt(File file) {
		return false;
	}

}
