package filesprocessing.Orders;

import java.io.*;
import java.util.*;

public class Reverse implements Comparator<File> {
	private Comparator<File> myComp;

	public Reverse(Comparator<File> inputOrder) {
		myComp = inputOrder;
	}

	@Override
	public int compare(File file1, File file2) {
		return (-myComp.compare(file1, file2));
	}
}
