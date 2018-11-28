package Property;
import Property.Variable;

import java.util.ArrayList;

public class Method {
    private String name;
    private ArrayList<Variable> variables;
    private int firstLine;

    public Method(String inpName, ArrayList<Variable> inpVariables, int firstLine){
        name = inpName;
        variables = inpVariables;
        this.firstLine = firstLine;
    }
    public Method(String inpName){
        name = inpName;
        variables = null;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Variable> getVariables(){
        return this.variables;
    }
    public int getFirstLine(){
        return this.firstLine;
    }
}
