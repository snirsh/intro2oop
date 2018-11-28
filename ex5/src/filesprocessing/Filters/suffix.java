package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class suffix extends nameFilter {
    suffix(String inputStr) {
        super(inputStr);
    }
    public boolean accept(File file){
        return(file.getName().split("\\.(?=[^\\.]+$)")[1].equals(condition));
    }
}
