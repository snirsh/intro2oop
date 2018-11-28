package filesprocessing.Filters;

import java.io.*;


/**
 * Created by Snir on 5/25/2016.
 */
public class sizeFilter implements FileFilter {
    protected static final int BYTE_SIZE = 1024;
    protected double condition;

    sizeFilter(double inputNum){
        condition = inputNum;
    }

    @Override
    public boolean accept(File file) {
        return false;
    }
}
