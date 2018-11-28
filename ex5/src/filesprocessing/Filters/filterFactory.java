package filesprocessing.Filters;

import filesprocessing.Type1Exception;

import java.io.*;

/**
 * Created by snirsh on 5/26/16.
 */
public class filterFactory {
    public FileFilter getFilter(String lineStr, int lineNum) throws Type1Exception {
        try {
            String[] lineArr = lineStr.split("#");
            FileFilter filter = null;
            switch (lineArr[0]) {
                case "greater_than":
                    checkNegNum(lineNum, Double.parseDouble(lineArr[1]));
                    filter = new greater_than(Double.parseDouble(lineArr[1]));
                    break;
                case "smaller_than":
                    checkNegNum(lineNum, Double.parseDouble(lineArr[1]));
                    filter = new smaller_than(Double.parseDouble(lineArr[1]));
                    break;
                case "between":
                    Double num1 = Double.parseDouble(lineArr[1]);
                    Double num2 = Double.parseDouble(lineArr[2]);
                    checkNegNum(lineNum, num1);
                    checkNegNum(lineNum, num2);
                    checkIfGreater(lineNum, num1, num2);
                    filter = new between(num1, num2);
                    break;
                case "file":
                    filter = new file(lineArr[1]);
                    break;
                case "contains":
                    filter = new contains(lineArr[1]);
                    break;
                case "prefix":
                    filter = new prefix(lineArr[1]);
                    break;
                case "suffix":
                    filter = new suffix(lineArr[1]);
                    break;
                case "writable":
                    checkBoolean(lineNum, lineArr[1]);
                    filter = new writable(lineArr[1].equals("YES") ? true : false);
                    break;
                case "executable":
                    checkBoolean(lineNum, lineArr[1]);
                    filter = new executable(lineArr[1].equals("YES")? true : false);
                    break;
                case "hidden":
                    checkBoolean(lineNum, lineArr[1]);
                    filter = new hidden(lineArr[1].equals("YES")? true : false);
                    break;
                case "all":
                    filter = new all();
                    break;
                default:
                    throw new Type1Exception(lineNum);
            }
            if (lineStr.contains("NOT")) {
                filter = new notFilter(filter);
            }
            return filter;
        }catch (NumberFormatException e){
            throw new Type1Exception(lineNum);
        }
    }

    private static void checkNegNum(int lineNum, double toCheck) throws Type1Exception {
        if (toCheck < 0) {
            throw new Type1Exception(lineNum);
        }
    }

    private static void checkBoolean(int lineNum, String toCheck) throws Type1Exception {
        if(!toCheck.equals("YES")&&!toCheck.equals("NO")){
            throw new Type1Exception(lineNum);
        }
    }
    private static void checkIfGreater(int lineNum, double n1, double n2) throws Type1Exception {
        if(n1>n2){
            throw new Type1Exception(lineNum);
        }
    }


}

