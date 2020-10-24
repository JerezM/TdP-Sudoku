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
    
    @Override
    public boolean equals(Object obj) {
        boolean iguales;

        if ( obj == null || obj.getClass() != this.getClass() ) {//Si obj es nulo o no es una clase EntryImpl
            iguales = false;
        }
        else {
            EntryImpl<K, V> entryObj = (EntryImpl<K, V> ) obj;
    
            iguales = ( this.key.equals( entryObj.getKey() ) && this.value.equals( entryObj.getValue() ) );
        }

        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = this.key.hashCode() + this.getValue().hashCode();
        
        return hash;
    }
}
