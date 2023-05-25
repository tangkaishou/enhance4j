package cn.tanglaoer.guava.cache;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public interface LRUCache<K, V> {
    void put(K key, V value);

    V get(K key);

    void remove(K key);

    int size();

    void clear();

    int limit();
}
