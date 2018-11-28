package filesprocessing.Filters;

import java.io.*;
/**
 * Created by Snir on 5/25/2016.
 */
public class nameFilter implements FileFilter {
    String condition;

    nameFilter(String inputStr){
        condition = inputStr;
    }

    @Override
    public boolean accept(File file) {
        return false;
    }
}
