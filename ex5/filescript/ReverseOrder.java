package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;
/**
 * Warper for the Comparator<File>, returns exactly the opposite the wrapped
 * Comparator.
 **/
public class ReverseOrder implements Comparator<File> {
	private Comparator<File> order;

	public ReverseOrder(Comparator<File> newOrder) {
		order = newOrder;
	}

	@Override
	public int compare(File file1, File file2) {
		return -order.compare(file1, file2);
	}

}
