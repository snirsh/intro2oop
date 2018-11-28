package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class executable extends booleanFilter {
    executable(boolean inputBoolean) {
        super(inputBoolean);
    }

    @Override
    public boolean accept(File file) {
        return condition? file.canExecute(): !file.canExecute();
    }
}
