package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;

/**
 * holds a filter, and order, and an error; made for convenience, as the command
 * file is divided into sections..
 */
public class Section implements Filter {
	protected Comparator<File> order;
	private Filter filter;
	protected String error;

	public Section(Filter newFilter, Comparator<File> newOrder, String newError) {
		order = newOrder;
		filter = newFilter;
		error = newError;
	}

	public Section(Filter newFilter, Comparator<File> newOrder) {
		order = newOrder;
		filter = newFilter;
		error = null;
	}

	public Section(Comparator<File> newOrder) {
		order = newOrder;
		filter = null;
		error = null;
	}

	public Section(Filter newFilter) {
		order = null;
		filter = newFilter;
		error = null;
	}

	public Section() {
		order = null;
		filter = null;
		error = null;
	}

	@Override
	public boolean FilterIt(File file) {
		return filter.FilterIt(file);
	}

}
