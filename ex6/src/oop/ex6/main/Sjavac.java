package oop.ex6.main;

import Property.Method;
import Property.Variable;
import utilities.Line;
import utilities.Parser;

import java.io.IOException;
import java.util.ArrayList;


import static oop.ex6.main.Interpreter.*;


public class Sjavac {

    public static ArrayList<Line> allLines;
    public static ArrayList<Method> methodsArray;
    public static int lineNum = 0;
    public static ArrayList<Variable> globalVariables;

    public static void main(String[] args) {

        boolean result = true;
        try {
            allLines = Parser.readLines(args[0]);
        } catch (IOException e) {
            System.err.println("2"+"\n"+e.getMessage());//TODO Throw "2" + Exception
            return;
        }

        //Check brackets validity
        if(!checkBrackets(allLines)){
            result = false; //TODO print 1 and exit
            System.out.println("Wrong brackets");
            return;

        }
        try {
            methodsArray = Interpreter.collectAllMethods(); //TODO Change to Tools.collectAllMethods
        } catch (Exception e) {
            result = false; //TODO print 1 and exit
        }
        globalVariables = new ArrayList<>();
        Interpreter interpreter = new Interpreter(allLines, globalVariables, true);

        //try{
            result = interpreter.execute(0);
       // }catch (Exception e){
            //result = ReturnState.Exception;
           // System.err.println("Error: Unsupported type "+e.getMessage());//TODO Throw SyntaxError
            //return;
       // }
        System.out.println(result);
        /**if (result){
            System.out.println("0");
        }
        else
            System.out.println("1");
         **/
    }



}
