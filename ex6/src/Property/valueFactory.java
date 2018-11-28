package Property;

/**
 * Created by yossi on 14/6/2016.
 */
public class valueFactory {

    public Object newVar(String type, String val) {
        switch (type) {
            case "int":
                //try {
                return new Integer((int) Double.parseDouble(val));
            //}catch (Exception e){
            //    System.err.println(e.getMessage());
            //}
            case "double":
                //try {
                return new Double(Double.parseDouble(val));
            //}catch (Exception e) {
            //   System.err.println(e.getMessage());
            //}
            case "String":
                //try{
                return new String(val);
            //}catch (Exception e) {
            //    System.err.println(e.getMessage());
            //}
            case "boolean":
                //try{
                return new Boolean(Boolean.parseBoolean(val));//TODO Check
            //}catch (Exception e) {
            //    System.err.println(e.getMessage());
            //}
            case "char":
                if (val.length() != 1) {
                    System.err.println("Expected char but found String");
                    break;
                }
                //try {
                return new Character(val.charAt(0));
            //}catch (Exception e) {
            //System.err.println(e.getMessage());
            // }
        }
        return null;
    }

}
