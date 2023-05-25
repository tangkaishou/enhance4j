package cn.tanglaoer.guava.cache;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * this class is not thread-safe class
 *
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {
    private final int limit;

    private final LinkedList<K> keys = new LinkedList<>();

    private final Map<K, V> cache = new HashMap<>();

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        // FIXME 这里不能为空？？?
        Preconditions.checkNotNull(value);
        if (keys.size() >= limit) {
            K oldestKey = keys.removeFirst();
            cache.remove(oldestKey);
        }
        keys.addLast(key);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        boolean exist = keys.remove(key);
        if (!exist) {
            return null;
        }
        keys.addLast(key);
        return cache.put(key, cache.get(key));
    }

    @Override
    public void remove(K key) {
        boolean exist = keys.remove(key);
        if (exist) {
            cache.remove(key);
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void clear() {
        this.keys.clear();
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        keys.stream().forEach(k -> builder.append(k).append("=").append(cache.get(k) + " "));
        return builder.toString();
    }
}
