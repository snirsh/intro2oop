package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class smaller_than extends sizeFilter{

    smaller_than(double inputNum) {
        super(inputNum);
    }

    public boolean accept(File file){
        return (file.length()/BYTE_SIZE < condition);
    }

}
