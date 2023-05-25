package cn.tanglaoer.guava.cache;


import java.util.concurrent.TimeUnit;

/**
 * 软引用测试 -Xmx10m -Xms10m -XX:+PrintGCDetails
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
public class SoftLRUCacheExample {
    public static void main(String[] args) throws InterruptedException {
        SoftLRUCache<String, byte[]> cache = new SoftLRUCache<>(100);
        for (int i = 0; i < 1000; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("The " + i + " entity ");
        }
    }
}
