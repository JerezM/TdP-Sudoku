package main.service.entry;

public interface Entry<K, V> {
    /**
     * Consulta la key actual.
     * @return Key actual.
     */
    public K getKey();

    /**
     * Consulta el value actual.
     * @return Value actual.
     */
    public V getValue();

    /**
     * Establece una nueva key, reemplazando la anterior.
     * @param key Nueva valor de la key.
     * @return Nueva key.
     */
    public K setKey(K key);

    /**
     * Establece un nueva value, reemplazando el anterior.
     * @param key Nuevo valor del value.
     * @return Nuevo value.
     */
    public V setValue(V value);
}
