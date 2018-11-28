package filesprocessing.Filters;

import java.io.*;

/**
 * Created by Snir on 5/25/2016.
 */
public class hidden extends booleanFilter{

    hidden(boolean inputBoolean) {
        super(inputBoolean);
    }

    @Override
    public boolean accept(File file) {
        return condition? !file.isHidden():file.isHidden();
    }
}
