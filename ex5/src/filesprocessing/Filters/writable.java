package filesprocessing.Filters;

import filesprocessing.Filters.booleanFilter;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class writable extends booleanFilter {
    writable(boolean inputBoolean) {
        super(inputBoolean);
    }

    @Override
    public boolean accept(File file) {
        return condition?file.canWrite():!file.canWrite();
    }
}
