package utilities;

public class Line {

    private String txt;
    private int lineNum;

    public Line(String txt, int lineNum){
        this.txt = txt;
        this.lineNum = lineNum;
    }

    public String getTxt(){
        return this.txt;
    }

    public int getLineNum(){
        return this.lineNum;
    }
}
