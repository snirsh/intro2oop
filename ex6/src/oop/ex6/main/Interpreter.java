//TODO Problem with a=b when b dosent exist
//TODO line num dosent increased when entering scope
package oop.ex6.main;

import Property.Method;
import Property.Variable;
import utilities.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Interpreter {

    private ArrayList<Line> lines;
    public static boolean isValid = true;
    //private int scopeCounter = 0;
    ArrayList<Variable> variables;
    private boolean isFirstScope;

    Method method;
    Variable variable;

    public Interpreter(ArrayList<Line> lines, ArrayList<Variable> variables, boolean isFirstScope) {//, int scopeCounter){
        this.lines = lines;
        //this.scopeCounter = scopeCounter;
        this.variables = new ArrayList<>();
        if (isFirstScope) {
            this.variables = Sjavac.globalVariables;
        }else{
            for (Variable var : variables) {
                this.variables.add(var);
            }
        }
        this.isFirstScope = isFirstScope;
    }

    public boolean execute(int lineNum) {
        for (;lineNum<lines.size();lineNum++) {
            String txtLine = Sjavac.allLines.get(lineNum).getTxt();
            if (!isValid)
                return false;
            else if (txtLine.isEmpty()||txtLine.startsWith("//"))
                //TODO add other comments variations
                continue;
            ArrayList<String> lineParts = clean(txtLine.split("(?!\\.)(\\W)"));
            lineParts.removeAll(Arrays.asList("", null));
            String firstWord = lineParts.get(0);
            //while (firstWord.equals("")) {
               // firstWord = lineParts.remove(0);
           // }
            if (Arrays.asList(Types.TYPES).contains(firstWord)) { //TODO change design of Types
                if (isFirstScope) {
                    isValid = addVariable(txtLine, Sjavac.globalVariables, false); //TODO change design
                }else {
                    isValid = addVariable(txtLine, this.variables, false);
                }
            } else if (firstWord.equals("final")) {
                isValid = addVariable(txtLine.replace("final ", ""), variables, true);
            } else if (firstWord.equals("void")) {
                int endBracket = findNextCloseBracket(this.lines);
                //TODO check Syntax
                //ArrayList<Line> sectionLines = new ArrayList<>(lines.subList(lineNum+1, endBracket-1));
                //Interpreter ip = new Interpreter(sectionLines, variables); //TODO check
                //isValid = ip.execute(); //TODO check
                lineNum = endBracket;
                continue;

            } else if (firstWord.equals("if")) {
                //scopeCounter++;
                boolean condition;
                if (!ifOrWhilePatternChecker(txtLine)){
                    isValid = false;
                    break;
                }else{
                    condition = conditionChecker(txtLine);
                }
                int temp = findCloseBracket(lines);
                if (condition) {
                    lineNum++;
                    int currentEndBracket = findCloseBracket(lines);
                    ArrayList<Line> sectionLines = new ArrayList<>(lines.subList(lineNum, currentEndBracket-1));
                    Interpreter child = new Interpreter(sectionLines, variables , false);
                    isValid = child.execute(lineNum);
                }
                lineNum = temp;
            } else if (firstWord.equals("while")) { //TODO logic
                //scopeCounter++;
                /**if (!ifOrWhileChecker(txtLine)){
                    isValid = false;
                    break;
                }**/
                if (!ifOrWhilePatternChecker(txtLine)) {
                    isValid = false;
                    break;
                }
                String conditionLine = txtLine;
                int endBracket = findCloseBracket(this.lines) + 1;
                ArrayList<Line> sectionLines = new ArrayList<>(lines.subList(lineNum, endBracket));
                Interpreter ip = new Interpreter(sectionLines, variables, false); //TODO check
                while(conditionChecker(conditionLine)){
                    isValid = ip.execute(++lineNum); //TODO check
                    continue;
                }
                lineNum = endBracket;
                //TODO FindNextBracket()
                //TODO ArrayList<String> newLines = from here until bracket;
                //TODO execute(newLines, variables)*
                // lineNum = next bracket txt number + 1

                //scopeCounter = 0;
            } else if ((method = searchMethod(firstWord)) != null) {  //A txt with a call for method
                int endBracket = findCloseBracket(this.lines);
                //TODO check Syntax
                ArrayList<Line> sectionLines = new ArrayList<>(lines.subList(method.getFirstLine(), endBracket-1));
                Interpreter ip = new Interpreter(sectionLines, variables, false); //TODO check
                isValid = ip.execute(method.getFirstLine()); //TODO check
                continue;

            }
            else if (firstWord.equals("return;")){
                continue;
            }
            if(Patterns.existingVarDeclaration.matcher(txtLine).matches()){
                variable = searchVariable(firstWord);
                if(variable!=null) {
                    if (variable.isFinal()) {
                        isValid = false;
                        break;
                    } else {
                        String newVal  = lineParts.get(1);
                        variable.setVal(newVal);
                    }
                }else{
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;

    }

    private static String typeChecker(String s){

    }

    private static boolean addVariable(String line, ArrayList<Variable> scopeVariables, boolean isFinal){

        Matcher m = Patterns.varDeclaration.matcher(line);
        boolean result = m.matches();;
        //System.out.println(txt+" "+method.matches());
        if (result){
            //String[] parts = txt.split("[\\s=]");
            ArrayList<String> lineParts = clean(line.split("(?!\\.)(\\W)"));
            lineParts.removeAll(Arrays.asList("", null));
            String type = lineParts.get(0), name = lineParts.get(1), val = lineParts.get(lineParts.size()-1);
            for (Variable v:scopeVariables) { //TODO vhane to search variable
                if (v.getName().equals(name))
                    return false;
            }
            scopeVariables.add(new Variable(type,name,val,false));
            if (isFinal)
                scopeVariables.get(scopeVariables.size()-1).setIsFinal(true);
        }
        return result;
    }

    public static ArrayList<String> clean(final String[] v) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(v));
        list.removeIf(e -> e.isEmpty()&&e.contentEquals("."));
        return list;
    }



    private static Method methodCreator(String txt, int lineNum) throws ExceptionInInitializerError{ //TODO change Exception
        Method result = null;
        Matcher m = Patterns.methodDeclaration.matcher(txt);
        //ArrayList<String> vars = new ArrayList<>();
        if(!m.matches()){
            //need to make an exception here because the txt is faulty
            //isValid = false;
            throw new ExceptionInInitializerError();
        }else{
            String name = m.group(0).split("\\(")[0].split(" ")[1];
            int pos = -1;
            String we = "";
            while(m.find(pos+1)){
                pos = m.start();
                we+=m.group(1);
            }
            we = we.replaceAll(",","");
            String[] we2= we.split(" ");
            ArrayList<String>we3 = new ArrayList<>();
            for(int i=1;i<we2.length;i+=2){
                if(we3.contains(we2[i])){
                    throw new ExceptionInInitializerError();
                }
            }
            ArrayList<Variable> methodVars = new ArrayList<>();
            for(int i=0; i<we3.size()-1;i++){
                methodVars.add(new Variable(we3.get(i),we3.get(i+1),null,false));
            }
            result = new Method(name, methodVars, lineNum);
        }
        return result;
    }


    public static ArrayList<Method> collectAllMethods() throws Exception {
        ArrayList<Method> methodsArray = new ArrayList<>();
        for (Line line:Sjavac.allLines){
            String firstWord = line.getTxt().split(" ")[0];
            if (firstWord.equals("void")){
                methodsArray.add(methodCreator(line.getTxt(),line.getLineNum()));
            }
        }
        /**for (int i=0; i<Sjavac.allLines.size();i++){
            try{
                methodsArray.add(methodCreator(Sjavac.allLines.get(i).getTxt(),i));
            }catch (Exception e){
                throw e;
            }
        }**/
        return methodsArray;
    }

    private Method searchMethod(String word){
        if (Sjavac.methodsArray!=null) {
            for (Method m : Sjavac.methodsArray) {
                if (word.equals(m.getName()))
                    return m;
            }
        }
        return null;
    }

    private Variable searchVariable(String word){
        for(Variable v:this.variables){
            if (word.equals(v.getName()))
                return v;
        }
        return null;
    }

    public static boolean checkBrackets(ArrayList<Line> lines){
        Stack<Character> stack =new Stack<>();
        Pattern openMatcherPattern = Pattern.compile("[{]");
        Pattern closeMatcherPattern = Pattern.compile("[}]$");
        for(Line line: lines){
            Matcher openMatcher = openMatcherPattern.matcher(line.getTxt());
            Matcher closeMatcher = closeMatcherPattern.matcher(line.getTxt());
            if(openMatcher.find()){
                stack.push('{');
            }
            if(closeMatcher.find()){
                if(stack.empty()){
                    return false;
                }else if(stack.peek().equals('{')){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private int findCloseBracket(ArrayList<Line> lines){ //TODO This may takes a lot of running time
        Line result = null;
        Stack<Character> stack =new Stack<>();
        Pattern openMatcherPattern = Pattern.compile("[{]");
        Pattern closeMatcherPattern = Pattern.compile("[}]$");
        for(Line line: lines){
            Matcher openMatcher = openMatcherPattern.matcher(line.getTxt());
            Matcher closeMatcher = closeMatcherPattern.matcher(line.getTxt());
            if(openMatcher.find()){
                stack.push('{');
            }
            else if(closeMatcher.find()){
                if(stack.empty()){
                    return -1;
                }else if(stack.peek().equals('{')){
                    stack.pop();
                }else{
                    return -1;
                }
                result = line;
            }
        }
        return result.getLineNum();
    }

    private boolean ifOrWhilePatternChecker(String line) {
        Matcher m = Patterns.ifPattern.matcher(line);
        return m.matches();
    }

    private boolean conditionChecker(String line) {
        boolean result = false;
        ArrayList<String> lineParts = clean(line.split("(?!\\.)(\\W)"));
        lineParts.removeAll(Arrays.asList("", null));
        boolean equal = line.contains("==");
        String v1 = lineParts.get(1), v2 = lineParts.get(2);
        Variable var1 = searchVariable(v1);
        Variable var2 = searchVariable(v2);
        if(v1.equals("true")||v1.equals("false")){
            result = (Boolean.parseBoolean(v1));
        }else if(v1.equals("!true")||v1.equals("!false")){
            result = !(Boolean.parseBoolean(v1.substring(1)));
        }
        if(var1!=null&&var1.getType()=="String"||var1.getType()=="char"){
            if(var2==null){
                result = (equal == (var1.getVal().equals(v2)));
            }else{
                result = (equal == (var1.getVal().equals(var2.getVal())));
            }
        }
        if (v2.equals("") && var2 == null){
            if (var1.getType().equals("boolean")){
                result = (boolean)var1.getVal();
            }
        }
        else if (var1 != null){
            if (var2 != null){
                try{
                    double d1 = Double.parseDouble(var1.getStrVal()), d2 = Double.parseDouble(var2.getStrVal());
                    result = (equal == (d1 == d2));
                }catch (Exception e){
                    //throw new not supported comparison;
                    System.err.println("unsupported comparison");
                    System.exit(0);
                }
            }else{
                try {
                    double d1 = Double.parseDouble(var1.getStrVal()), d2 = new Double(v2);
                    result  = (equal == (d1 == d2));
                }catch (Exception e){
                    System.err.println("unsupported comparison");
                    System.exit(0);
                }
            }
        }else if (var1 == null) {
            if(var2 == null) {
                if (v1.matches("\\d+") && v2.matches("\\d+")) {
                    result = (equal == ((new Double(v1)) == (new Double(v2))));
                }else if(v1.matches("\\w+") && v2.matches("\\w+")) {
                    result = (equal == v1.equals(v2));
                }
                else if(v1.matches("\\d+")&& v2.matches("\\w+")|| v2.matches("\\d+")&& v1.matches("\\w+")){
                    System.err.println("unsupported comparison");
                    System.exit(0);
                }
                else {
                    System.err.println("Unrecognized variable");
                    System.exit(0);
                }
            }
        }
        if (var2 != null && !v1.equals("")) {
            try {
                double d1 = new Double(v1), d2 = Double.parseDouble(var2.getStrVal());
                result = (equal == (d1 == d2));
            } catch (Exception e) {
                System.err.println("unsupported comparison");
                System.exit(0);
            }
        }
        return result;
    }

    private int findNextCloseBracket(ArrayList<Line> lines){
        for(Line line:lines){
            if (line.getTxt().matches("^\\s*}$")){
                return line.getLineNum();
            }
        }
        return -1;
    }

}

