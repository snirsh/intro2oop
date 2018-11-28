package oop.ex5.filescript;

import java.io.File;
import java.util.Comparator;

public class OrderFactory {
	private String[] values;
	private Comparator<File> order;
	private ReverseOrder reverseOrder;

	public OrderFactory() {

	}
	/**
	 * @param String
	 *            , int
	 * @returns an Order according to the line
	 * @throws type1Exepsions
	 *             for warnings with line number
	 **/
	public Comparator<File> getOrder(String line, int lineNumber)
			throws Type1Exception {
		if (line == null) {
			return new pathOrder();
		} else {
			values = line.split("#");
			switch (values[0]) {
			case "abs":
				order = new pathOrder();
				break;
			case "type":
				order = new typeOrder();
				break;
			case "size":
				order = new SizeOrder();
				break;
			default:
				// none of the existing Orders or wrong format, throw
				// exception.
				throw new Type1Exception(lineNumber);
			}
			if (line.contains("#REVERSE")) {
				reverseOrder = new ReverseOrder(order);
				return reverseOrder;
			}
			return order;
		}
	}
}
