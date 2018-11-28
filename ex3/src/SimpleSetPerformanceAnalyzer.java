/*Imports*/
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * SimpleSetPerformanceAnalyzer - this class will analyze the data for us.
 */
public class SimpleSetPerformanceAnalyzer {
    /*
    ALL OF OUR FIELDS
     */

    private CollectionFacadeSet tree;
    private CollectionFacadeSet linked;
    private CollectionFacadeSet hash;
    private OpenHashSet open;
    private ClosedHashSet closed;
    private SimpleSet[] dataArr = new SimpleSet[5];
    private final static int DIVISION = 1000000;
    private final static String HI = "hi";
    private final static String SAME_HASH_STRING = "-13170890158";
    private final static String TWENTY_THREE = "23";
    private final static int LINKED=3;

    /**
     * SimpleSetPerformanceAnalyzer's Constructor
     */
    public SimpleSetPerformanceAnalyzer(){
        //creating a new Collectionfacade wrapper for each DataStructure and putting it in an Array
        tree = new CollectionFacadeSet(new TreeSet());
        dataArr[4]=tree;
        linked = new CollectionFacadeSet(new LinkedList());
        dataArr[3]=linked;
        hash = new CollectionFacadeSet(new HashSet());
        dataArr[2]=hash;
        open = new OpenHashSet();
        dataArr[1]=open;
        closed = new ClosedHashSet();
        dataArr[0]=closed;
    }

    /**
     * short code to show us how far we're in the data list
     * @param length = the data list's length
     * @param i = index we're at now
     */
    private void howMuchLonger(int length, int i){
        if((float)i/length==(float)1/(float)4){
            System.out.println("25%");
            return;
        }else if((float)i/length==(float)1/(float)2){
            System.out.println("50%");
            return;
        }else if((float)i/length==(float)3/(float)4){
            System.out.println("75%");
            return;
        }

    }

    /**
     * this method will show the running times for the Data files on each data structure we have
     * @param data = data file as String array
     */
    private void dataComparison(String[] data){
        long john; //silver
        for(int i=0; i<dataArr.length; i++) {
            john = System.nanoTime();
            for (int j=0; j<data.length; j++) {
                // adding all the elements
                dataArr[i].add(data[j]);
                howMuchLonger(data.length, j);
            }
            long difference = System.nanoTime() - john;
            System.out.println("on data" + i + "data comparison: " + difference / DIVISION);
        }
    }

    /**
     * this method will show us the time it takes for each data set to find a word
     * @param phrase - what we want to find
     * @param data - data file as string array
     */
    private void containsComparison(String phrase, String[] data){
        long difference;
        long longTimeAgo; //in a galaxy far far away
        for(int i=0; i<dataArr.length; i++){
            // putting the data file in our data structure
            for(String str:data){
                dataArr[i].add(str);
            }
            //Warm up for every data structure except linked list
            if(i!=LINKED){
                for(int j=0;j<70000;j++){
                    dataArr[i].contains(phrase);
                }
            }
            // the real answer
            longTimeAgo = System.nanoTime();
            boolean answer = dataArr[i].contains(phrase);
            difference = System.nanoTime()-longTimeAgo;
            System.out.println("on data " + i + " contains difference: " + difference + " " + answer);
        }
    }

    /**
     * our main that runs all the tests and creates most of the prints
     * @param args
     */
    public static void main(String[] args){
        SimpleSetPerformanceAnalyzer newAnalyzer = new SimpleSetPerformanceAnalyzer();
        String[] data1 = Ex3Utils.file2array("data1.txt");
        String[] data2 = Ex3Utils.file2array("data2.txt");
        System.out.println("#data comparison with data1.txt: ");
        newAnalyzer.dataComparison(data1);
        newAnalyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("#data comparison with data2.txt: ");
        newAnalyzer.dataComparison(data2);
        newAnalyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("#contains comparison with 'hi' and data1.txt: ");
        newAnalyzer.containsComparison(HI, data1);
        newAnalyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("#contains comparison with '-13170890158' and data1.txt: ");
        newAnalyzer.containsComparison(SAME_HASH_STRING, data1);
        newAnalyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("#contains comparison with '23' and data2.txt: ");
        newAnalyzer.containsComparison(TWENTY_THREE, data2);
        newAnalyzer = new SimpleSetPerformanceAnalyzer();
        System.out.println("#contains comparison with 'HI' and data2.txt: ");
        newAnalyzer.containsComparison(HI, data2);
    }
}

