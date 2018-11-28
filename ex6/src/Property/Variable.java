package Property;

public class Variable {

    private Object val;
    private String strVal;
    private String name;
    private String type;
    private boolean isFinal = false;
    private valueFactory VF = new valueFactory();

    public Variable(String type, String name, String val, boolean isFinal)  {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.val = VF.newVar(type,val);
        this.strVal = val;
    }

    public Object getVal() {
        return val;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isFinal(){
        return this.isFinal;
    }

    public void setIsFinal(boolean newIsFinal){
        this.isFinal = newIsFinal;
    }

    public void setVal(String newVal){
        this.val = VF.newVar(type,newVal);
    }

    public String getStrVal(){
        return this.strVal;
    }


}
