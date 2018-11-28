/**
 * Created by snirsh on 4/5/16.
 */
public class ClosedHashSet extends SimpleHashSet {
    private int index = 0;
    public String[] set;

    public ClosedHashSet(){
        set = new String[INITIAL_CAPACITY];
    }

    public ClosedHashSet(float upper, float lower){
        upperLoadFactor=upper;
        lowerLoadFactor=lower;
        set = new String[INITIAL_CAPACITY];
    }

    public ClosedHashSet(String[] data){
        set = new String[INITIAL_CAPACITY];
        for(String str: data){
            if(str!=null){
                add(str);
            }
        }
    }

    public void rehash(boolean inc){
        ClosedHashSet newSet = new ClosedHashSet();
        int newCapacity=capacity();
        if(inc){
            newCapacity*=MULTIPLIER;
        }else{
            newCapacity/=MULTIPLIER;
        }
        newSet.set = new String[newCapacity];
        for(int i=0;i<capacity();i++){
            if(set[i]!=null&&!set[i].equals(FLAG)){
                newSet.add(set[i]);
            }
        }
        set = newSet.set;
    }

    @Override
    public boolean add(String newValue) {
        index = 0;
        int hash=newValue.hashCode();
        if(this.contains(newValue)){
            return false;
        }
        index=0;
        while(set[clamp(index, hash)]!=null&&!set[clamp(index, hash)].equals(FLAG)&&index<capacity()){
            index++;
        }
        set[clamp(index,hash)]=newValue;
        size++;
        float loadFactor = (float) size()/(float) capacity();
        if(loadFactor>getUpperLoadFactor()){
            rehash(INCREASE);
        }
        return true;
    }

    @Override
    public boolean contains(String searchVal) {
        index = 0;
        int hash=searchVal.hashCode();
        while(set[clamp(index,hash)]!=null&&index<size()){
            if(set[clamp(index,hash)].equals(searchVal)){
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public boolean delete(String toDelete) {
        index = 0;
        int hash=toDelete.hashCode();
        if(!this.contains(toDelete)){
            return false;
        }
        while(!set[clamp(index, hash)].equals(toDelete)){
            index++;
        }
        set[clamp(index,hash)]=FLAG;
        size--;
        float loadFactor = (float) size()/(float) capacity();
        if(loadFactor<getLowerLoadFactor()){
            rehash(DECREASE);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    int clamp(int i, int hash) {
        return ((hash)+(i+i*i)/MULTIPLIER)&(capacity()-1);
    }

    @Override
    int capacity() {
        return set.length;
    }
}
