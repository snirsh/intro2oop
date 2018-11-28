package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class notFilter implements FileFilter {
    FileFilter filter;
    notFilter(FileFilter inputFilter){
        filter=inputFilter;
    }
    public boolean accept(File file){
        return !filter.accept(file);
    }
}
