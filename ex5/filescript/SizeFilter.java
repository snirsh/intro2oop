package oop.ex5.filescript;

import java.io.File;
/** 
 *  class of all size filters
 **/
public class SizeFilter implements Filter {
	protected double conditionData;

	SizeFilter(double num) {
		conditionData = num;
	}

	@Override
	public boolean FilterIt(File file) {
		return false;
	}

}
