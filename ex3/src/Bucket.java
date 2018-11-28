import java.util.LinkedList;

/**
 * A bucket that will wrap linked list **FOR USE IN OPENHASHSET*
 */
public class Bucket extends LinkedList<String> {
    public LinkedList<String> buck;
    public Bucket(){
        buck = new LinkedList<>();
    }
}