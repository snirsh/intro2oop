package filesprocessing;

/**
 * Created by Snir on 5/25/2016.
 */
public class Type1Exception extends Exception {
    public int lineNum;
    public Type1Exception(int exLineNum){
        lineNum = exLineNum;
    }
}
