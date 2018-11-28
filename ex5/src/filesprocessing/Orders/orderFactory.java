package filesprocessing.Orders;

import filesprocessing.Type1Exception;

import java.io.*;
import java.util.Comparator;

/**
 * Created by snirsh on 5/26/16.
 */
public class orderFactory {
    public Comparator<File> getOrder(String lineStr, int lineNum) throws Type1Exception {
        String[] lineArr = lineStr.split("#");
        Comparator<File> order=null;
        switch (lineArr[0]){
            case "abs": order = new abs(); break;
            case "type": order = new type(); break;
            case "size": order = new sizeOrder(); break;
            default: throw new Type1Exception(lineNum);
        }
        if(lineStr.contains("REVERSE")){
            order = new Reverse(order);
        }
        return order;
    }
}
