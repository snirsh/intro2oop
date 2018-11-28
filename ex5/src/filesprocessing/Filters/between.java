package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class between implements FileFilter {
    private greater_than lowerBound;
    private smaller_than upperBound;

    between(double inputLower, double inputUpper) {
        upperBound = new smaller_than(inputLower);
        lowerBound = new greater_than(inputUpper);
    }

    @Override
    public boolean accept(File file) {
        return !upperBound.accept(file) && !lowerBound.accept(file);
    }
}
