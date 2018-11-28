package oop.ex5.filescript;

@SuppressWarnings("serial")
/**
 * warning exception, holds line number for printing.
 */
public class Type1Exception extends Exception {
	public int lineNumber;

	public Type1Exception(int num) {
		lineNumber = num;
	}

}
