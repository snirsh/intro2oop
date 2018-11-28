package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public abstract class booleanFilter implements FileFilter{
    protected boolean condition;
    booleanFilter(boolean inputBoolean){
        condition = inputBoolean;
    }

    @Override
    public abstract boolean accept(File file);
}
