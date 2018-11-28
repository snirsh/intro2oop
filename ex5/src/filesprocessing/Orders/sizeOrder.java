package filesprocessing.Orders;

import java.io.*;
import java.util.*;

public class sizeOrder implements Comparator<File> {

	public int compare(File file1, File file2) {
		if (file1.length() > file2.length()) {
			return 1;
		}
		if (file1.length() < file2.length()) {
			return -1;
		}
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}

}
