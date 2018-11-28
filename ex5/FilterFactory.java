package oop.ex5.filescript;

public class FilterFactory {
	private Double greaterThan;
	private Double smallerThan;
	private boolean yesOrNo;
	private String booleanStr;
	private String[] values;
	private Filter filter;
	private NotFilter notFilter;

	public FilterFactory() {

	}

	/**
	 * @param String
	 *            , int
	 * @returns a filter according to the line
	 * @throws type1Exeprions
	 *             for warnings with line number
	 **/
	public Filter getFilter(String line, int lineNumber) throws Type1Exception {
		try {
			values = line.split("#");
			switch (values[0]) {
			case "greater_than":
				greaterThan = Double.parseDouble(values[1]);
				checkIfNegetive(greaterThan, lineNumber);
				filter = new GreaterThanFilter(greaterThan);
				break;
			case "between":
				greaterThan = Double.parseDouble(values[1]);
				checkIfNegetive(greaterThan, lineNumber);
				smallerThan = Double.parseDouble(values[2]);
				checkIfNegetive(smallerThan, lineNumber);
				if (smallerThan <= greaterThan) {
					throw new Type1Exception(lineNumber);
				}
				filter = new BetweenFilter(greaterThan, smallerThan);
				break;
			case "smaller_than":
				smallerThan = Double.parseDouble(values[1]);
				checkIfNegetive(smallerThan, lineNumber);
				filter = new SmallerThanFilter(smallerThan);
				break;
			case "file":
				filter = new NameEqualsFilter(values[1]);
				break;
			case "contains":
				filter = new NameContainsFilter(values[1]);
				break;
			case "prefix":
				filter = new PrefixNameFilter(values[1]);
				break;
			case "suffix":
				filter = new SuffixNameFilter(values[1]);
				break;
			case "writable":
				booleanStr = values[1];
				yesOrNo = checkIfYesNo(booleanStr, lineNumber);
				filter = new WritableFilter(yesOrNo);
				break;
			case "executable":
				booleanStr = values[1];
				yesOrNo = checkIfYesNo(booleanStr, lineNumber);
				filter = new ExecutableFilter(yesOrNo);
				break;
			case "hidden":
				booleanStr = values[1];
				yesOrNo = checkIfYesNo(booleanStr, lineNumber);
				filter = new HiddenFilter(yesOrNo);
				break;
			case "all":
				filter = new AllFilter();
				break;
			default:
				// none of the existing filters or wrong format, throw
				// exception.
				throw new Type1Exception(lineNumber);
			}
			if (line.contains("#NOT")) {
				notFilter = new NotFilter(filter);
				return notFilter;
			}
			return filter;
			// if something that is not a number is found in a place where a
			// number should be
		} catch (NumberFormatException e) {
			throw new Type1Exception(lineNumber);
		}
	}

	// helper method,checking if the numbers are negative for the sizeFilters
	private static void checkIfNegetive(double num, int lineNumber)
			throws Type1Exception {
		if (num < 0) {
			throw new Type1Exception(lineNumber);
		}
	}

	// checks if where expected there is a "YES" or "NO"
	private boolean checkIfYesNo(String str, int lineNumber)
			throws Type1Exception {
		if (str.equals("YES")) {
			return true;
		}
		if (str.equals("NO")) {
			return false;
		}
		throw new Type1Exception(lineNumber);
	}

}
