package main.service.entry;


public class EntryImpl<K, V> implements Entry<K, V> {

    private K key;
    private V value;

    public EntryImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public K setKey(K key) {
        this.key = key;

        return key;
    }

    @Override
    public V setValue(V value) {
        this.value = value;

        return value;
    }
    
}
