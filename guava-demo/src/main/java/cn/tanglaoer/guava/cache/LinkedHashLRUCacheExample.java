package cn.tanglaoer.guava.cache;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public class LinkedHashLRUCacheExample {
    public static void main(String[] args) {
        LinkedHashLRUCache<String, String> cache = new LinkedHashLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        System.out.println(cache);
    }
}
