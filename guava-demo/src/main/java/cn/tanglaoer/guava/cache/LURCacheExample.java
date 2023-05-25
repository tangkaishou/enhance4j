package cn.tanglaoer.guava.cache;

import java.util.concurrent.TimeUnit;

/**
 * 导致OOM问题
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
public class LURCacheExample {
    public static void main(String[] args) throws InterruptedException {
        LinkedHashLRUCache<String, byte[]> cache = new LinkedHashLRUCache<>(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("The " + i + " entity ");
        }
    }
}
