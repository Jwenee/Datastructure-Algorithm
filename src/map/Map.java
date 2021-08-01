package map;

public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    V get(K key);
    void set(K key, V newVal);
    int getSize();
    boolean contains(K key);
    boolean isEmpty();
}
