/**
 * Created by snirsh on 4/6/16.
 */
public abstract class SimpleHashSet implements SimpleSet{
    public int size = 0;
    public float lowerLoadFactor=0.25f;
    public float upperLoadFactor=0.75f;
    public static final int INITIAL_CAPACITY=16;
    public static final String FLAG = "**DELETED**";
    public static final boolean DECREASE = false;
    public static final boolean INCREASE = true;
    public static final int MULTIPLIER = 2;


    public double getLowerLoadFactor(){
        return lowerLoadFactor;
    }
    public double getUpperLoadFactor(){
        return upperLoadFactor;
    }

    abstract int clamp(int i, int hash);
    abstract int capacity();
    public int size(){
        return size;
    }
    abstract void rehash(boolean inc);
}
