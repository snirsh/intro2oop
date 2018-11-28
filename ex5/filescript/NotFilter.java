package oop.ex5.filescript;

import java.io.File;

/**
 * Warper for the filter interface, returns exactly the opposite the wrapped
 * filter.
 **/
public class NotFilter implements Filter {
	Filter filter;

	public NotFilter(Filter newFilter) {
		filter = newFilter;
	}

	@Override
	public boolean FilterIt(File file) {
		return !filter.FilterIt(file);
	}

}
