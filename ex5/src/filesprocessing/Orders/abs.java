package filesprocessing.Orders;

import java.io.*;
import java.util.*;

public class abs implements Comparator<File> {

	public int compare(File firstFile, File secondFile) {
		return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
	}

}
