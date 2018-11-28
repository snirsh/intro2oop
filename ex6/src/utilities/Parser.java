package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Parser {


    /**
     * Reads the lines one by one from the command file. for every txt construct a Line data structure.
     * @param filename File name as string
     * @return A linkedList contains all Lines in order
     * @throws IOException
     */
    public static ArrayList<Line> readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<Line> lines = new ArrayList<>();
        String txtLine;
        int i = 0;
        while ((txtLine = bufferedReader.readLine()) != null) {
            lines.add(new Line(txtLine,i));
            i++;
        }
        bufferedReader.close();
        return lines;
    }
}
