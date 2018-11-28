package filesprocessing;

import java.io.*;
import java.util.Comparator;

/**
 * Created by snirsh on 5/26/16.
 */
public class Section implements FileFilter {
    protected Comparator<File> order;
    protected String errorStr;
    protected FileFilter filter;

    public Section(FileFilter filterInput, Comparator<File> orderInput, String errInput){
        filter = filterInput;
        order = orderInput;
        errorStr = errInput;
    }

    @Override
    public boolean accept(File pathname) {
        return filter.accept(pathname);
    }
}
