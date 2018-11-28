package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class all implements FileFilter{
    @Override
    public boolean accept(File pathname) {
        return true;
    }
}
