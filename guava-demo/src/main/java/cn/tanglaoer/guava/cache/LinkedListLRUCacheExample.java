package cn.tanglaoer.guava.cache;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public class LinkedListLRUCacheExample {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedListLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);
        cache.put("4", "4");
        cache.get("2");
        System.out.println(cache);
    }
}
