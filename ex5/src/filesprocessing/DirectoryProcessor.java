package filesprocessing;

import filesprocessing.Filters.all;
import filesprocessing.Filters.filterFactory;
import filesprocessing.Orders.abs;
import filesprocessing.Orders.orderFactory;

import java.io.*;
import java.util.*;
/**
 * Created by snirsh on 5/26/16.
 */
public class DirectoryProcessor {


    public static void main(String[] args){
        filterFactory fFactory = new filterFactory();
        orderFactory oFactory = new orderFactory();
        File sourcePath = new File(args[0]);
        File commandPath = new File(args[1]);
        LineNumberReader commandReader = null;
        String line;
        try {
            if (!sourcePath.isDirectory() || !commandPath.isFile()) {
                System.err.println("ERROR");
                return;
            }
            File[] files = sourcePath.listFiles();
            commandReader = new LineNumberReader(new FileReader(commandPath));
            ArrayList<Section> sections = new ArrayList<>();
            while ((line = commandReader.readLine()) != null) {
                sections.add(sectionBuilder(commandReader, line));
            }
            //gief resaltz
            resultsPrinter(sections, files);
        }catch (FileNotFoundException e){
            System.err.println("ERROR");
            return;
        } catch (IOException| Type2Exception e) {
            System.err.println("ERROR");
            return;
        }
    }

    private static void resultsPrinter(ArrayList<Section> sections, File[] files) {
        ArrayList<File> toPrint = new ArrayList<>();
        for(Section section: sections){
            toPrint.clear();
            for (File file: files){
                if(section.errorStr!=null){
                    System.out.println(section.errorStr);
                    break;
                }else if(file.isFile()&&section.accept(file)){
                    toPrint.add(file);
                }
            }
            toPrint = sortArrayList(toPrint, section);
            for (int i=0;i<toPrint.size();i++){
                System.out.println(toPrint.get(i).getName());
            }
        }
    }

    private static ArrayList<File> sortArrayList(ArrayList<File> toPrint, Section section) {
        int n = toPrint.size();
        File temp = null;
        for(int i=0; i<n;i++){
            for (int j=1; j<(n-i);j++){
                if(section.order.compare(toPrint.get(j-1),toPrint.get(j))>0){
                    temp = toPrint.get(j-1);
                    toPrint.set(j-1,toPrint.get(j));
                    toPrint.set(j,temp);
                }
            }
        }
        return toPrint;
    }

    private static Section sectionBuilder(LineNumberReader commandReader, String line)throws Type2Exception,IOException{
        filterFactory fFactory = new filterFactory();
        orderFactory oFactory = new orderFactory();
        FileFilter filter = new all();
        Comparator<File> order = new abs();
        String err = null;
        if(!line.equals("FILTER")){
            throw new Type2Exception();
        }
        try{ //trying to create a filter
            line = commandReader.readLine();
            if(!line.equals("ORDER")&&line!=null) {
                filter = fFactory.getFilter(line, commandReader.getLineNumber());
            }
        }catch (Type1Exception e){
            err = "Warning in line " + e.lineNum;
        }
        if(line==null) {
            throw new Type2Exception();
        }//trying to create an order
        if(!line.equals("ORDER")){
            line = commandReader.readLine();
        }
        if(line==null){
            throw new Type2Exception();
        }
        try {
            commandReader.mark(0);
            line = commandReader.readLine();
            if(line.equals("FILTER")){
                commandReader.reset();
                return new Section(filter,order,err);
            }
            if (!line.equals("FILTER") && line != null) {
                order = oFactory.getOrder(line, commandReader.getLineNumber());
            }
        } catch (Type1Exception e) {
            if(err!=null){
                err+="\nWarning in line " + e.lineNum;
            }else{
                err = "Warning in line " + e.lineNum;
            }
        }
        return new Section(filter, order, err);
    }




}
