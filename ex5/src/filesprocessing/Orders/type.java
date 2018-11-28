package filesprocessing.Orders;

import java.io.*;
import java.util.*;

public class type implements Comparator<File> {

	public int compare(File file1, File file2) {
		String file1_suffix=file1.getName().split("\\.(?=[^\\.]+$)")[1];
		String file2_suffix=file2.getName().split("\\.(?=[^\\.]+$)")[1];
		if (!file1_suffix.equals(file2_suffix)){
			return file1_suffix.compareTo(file2_suffix);
		}
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}

}
