package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class file extends nameFilter {
    file(String inputStr) {
        super(inputStr);
    }
    public boolean accept(File file){
        return file.getName().equals(condition);
    }
}
