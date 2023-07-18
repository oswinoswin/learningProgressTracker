package tracker;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Controller<K,V> {
    Map<K,V> data;

    public Controller() {
        data = new LinkedHashMap<>();
    }

    public boolean contains(K key){
        return data.containsKey(key);
    }

    public void insert(K key, V value){
        data.put(key, value);
    }
}
