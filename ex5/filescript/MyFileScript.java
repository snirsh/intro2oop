package oop.ex5.filescript;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class MyFileScript {

	/**
	 * @param args
	 *            (source folder command file) builds sections according to the
	 *            command file prints the names of the files who pass the filter
	 *            according to the order for each section
	 */
	public static void main(String[] args) {

		File sourceDir = new File(args[0]);
		File commands = new File(args[1]);
		Section section;
		try {
			checkForType2Errors(sourceDir, commands);
			LineNumberReader reader = new LineNumberReader(new FileReader(
					commands));
			File[] allFiles = sourceDir.listFiles();
			ArrayList<Section> sections = new ArrayList<Section>();
			String line = null;
			FilterFactory filterFactory = new FilterFactory();
			OrderFactory orderFactory = new OrderFactory();
			while ((line = reader.readLine()) != null) {
				section = createSection(orderFactory, filterFactory, reader,
						line, sections);
				sections.add(section);
			}
			printResults(sections, allFiles);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR");
			return;
		} catch (Type2Exeption e) {
			System.err.println("ERROR");
			return;
		} catch (IOException e) {
			System.err.println("ERROR");
			return;
		}
	}

	// checks for type2Exceptions in the commands file, or source folder.
	private static void checkForType2Errors(File folder, File file)
			throws Type2Exeption {
		if (file.isFile() != true) {
			throw new Type2Exeption();
		}
		if (folder.isDirectory() != true) {
			throw new Type2Exeption();
		}
	}

	// actually builds the sections, one at a time, throws Type2Exception, and
	// handles Type1Exceptions.
	private static Section createSection(OrderFactory orderFactory,
			FilterFactory filterFactory, LineNumberReader reader, String line,
			ArrayList<Section> sections) throws Type2Exeption, IOException {
		Filter filter;
		Comparator<File> order = null;
		String error = null;
		if (!line.equals("FILTER")) {
			throw new Type2Exeption();
		}
		try {
			line = reader.readLine();
			if (!line.equals("ORDER")) {
				filter = filterFactory.getFilter(line, reader.getLineNumber());
			} else {
				filter = new AllFilter();
			}
		} catch (Type1Exception e) {
			filter = new AllFilter();
			error = "Warning in line " + e.lineNumber;
		} finally {
			line = reader.readLine();
		}
		reader.mark(0);
		if (line == null || !line.equals("ORDER")) {
			throw new Type2Exeption();
		}
		line = reader.readLine();
		if (line == null) {
			order = new pathOrder();
		} else {
			if (line.equals("FILTER")) {
				order = new pathOrder();
				reader.reset();
			} else {
				try {
					order = orderFactory.getOrder(line, reader.getLineNumber());
				} catch (Type1Exception e) {
					order = new pathOrder();
					if (error != null) {
						error += "\nWarning in line " + e.lineNumber;
					} else {
						error = "Warning in line " + e.lineNumber;
					}
				}
			}
		}
		return new Section(filter, order, error);
	}

	// prints the results, section after section, error first if exists.
	private static void printResults(ArrayList<Section> sections,
			File[] allFiles) {
		Section currentSection;
		ArrayList<File> currentFiles;
		while (!sections.isEmpty()) {
			currentFiles = new ArrayList<File>();
			currentSection = sections.remove(0);
			for (File file : allFiles) {
				if (currentSection.FilterIt(file) && file.isFile()) {
					currentFiles.add(file);
				}
			}
			currentFiles.sort(currentSection.order);
			if (currentSection.error != null) {
				System.out.println(currentSection.error);
			}
			for (File file : currentFiles) {
				System.out.println(file.getName());

			}
		}
	}
}
