/**
 * Created by snirsh on 4/5/16.
 */
public class CollectionFacadeSet implements SimpleSet{
    protected java.util.Collection<String> collection;
    public CollectionFacadeSet(java.util.Collection<String> set){
        collection = set;
    }
    @Override
    public boolean add(String newValue) {
        if(!contains(newValue)){
            return collection.add(newValue);
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if(contains(toDelete)){
            return collection.remove(toDelete);
        }
        return false;
    }

    @Override
    public int size() {
        return collection.size();
    }
}
