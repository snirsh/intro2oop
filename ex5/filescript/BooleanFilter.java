package oop.ex5.filescript;

import java.io.File;
/** 
 * abstract class of all boolean filters
 **/
public abstract class BooleanFilter implements Filter {
	boolean conditionData;

	public BooleanFilter(boolean bool) {
		conditionData = bool;
	}

	public abstract boolean FilterIt(File file);
}
