package oop.ex5.filescript;

import java.io.File;

public class BetweenFilter implements Filter {
	private GreaterThanFilter upper;
	private SmallerThanFilter lower;

	public BetweenFilter(double lowerBound, double upperbound) {
		upper = new GreaterThanFilter(upperbound);
		lower = new SmallerThanFilter(lowerBound);
	}

	/**
	 * @param File
	 * @returns true for files greater then double lower bound and smaller then
	 *          upper bound
	 **/
	@Override
	public boolean FilterIt(File file) {
		return upper.FilterIt(file) == false && lower.FilterIt(file) == false;
	}
}
