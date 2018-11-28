/**
 * Created by snirsh on 4/5/16.
 */

public class OpenHashSet extends SimpleHashSet {
    private int index = 0;
    public Bucket[] set;

    public OpenHashSet(){
        set = new Bucket[INITIAL_CAPACITY];
    }

    public OpenHashSet(float upper, float lower){
        upperLoadFactor=upper;
        lowerLoadFactor=lower;
        set = new Bucket[INITIAL_CAPACITY];
    }

    public OpenHashSet(String[] data) {
        set = new Bucket[INITIAL_CAPACITY];
        for (String str : data){
            if (str != null) {
                add(str);
            }
        }
    }

    @Override
    int clamp(int i, int hash) {
        return((hash)&(capacity()-1));
    }


    @Override
    public boolean add(String newValue) {
        if(contains(newValue)){
            return false;
        }
        if(set[clamp(index,newValue.hashCode())]==null){
            set[clamp(index,newValue.hashCode())]= new Bucket();
        }
        set[clamp(index,newValue.hashCode())].add(newValue);
        size++;
        float loadFactor = (float) size()/(float) capacity();
        if(loadFactor>getUpperLoadFactor()){
            rehash(INCREASE);
        }
        return true;
    }

    @Override
    public boolean contains(String searchVal) {
        index = clamp(index, searchVal.hashCode());
        if (set[index]==null){
            return false;
        }
        return set[index].contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if(!contains(toDelete)){
            return false;
        }
        set[clamp(index,toDelete.hashCode())].remove(toDelete);
        size--;
        float loadFactor = (float)size()/(float)capacity();
        if(loadFactor<getLowerLoadFactor()){
            rehash(DECREASE);
        }
        return true;
    }
    @Override
    public void rehash(boolean inc){
        OpenHashSet newOpenSet = new OpenHashSet();
        int newCapacity = capacity();
        if(inc){
            newCapacity*=MULTIPLIER;
        }else{
            newCapacity/=MULTIPLIER;
        }
        newOpenSet.set = new Bucket[newCapacity];
        for(int i=0;i<capacity();i++){
            while(set[i]!=null){
                String nextStr = set[i].poll();
                if(nextStr==null){
                    break;
                }
                newOpenSet.add(nextStr);
            }
        }
        set = newOpenSet.set;
    }

    @Override
    int capacity() {
        return set.length;
    }
}
