package cn.tanglaoer.guava.cache;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.Test;

import javax.swing.plaf.SpinnerUI;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
@Slf4j
public class CacheLoaderTest2 {
    public static void main(String[] args) {

    }

    @Test
    public void testEvictionByAccessTime() throws ExecutionException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(this.createCacheLoader());
        cache.get("tanglaoer");
        log.info("cache size{}", cache.size());

        Employee guava = cache.getUnchecked("Guava");
        log.info("guava:{}", guava);
        cache.put("dd", null);

        Employee aNull = cache.get("dd");
    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return CacheLoader.from(key -> new Employee(key, key, key));
    }

    /**
     * strong/soft/weak/phantom reference
     */
    @Test
    public void testWeakKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(this.createCacheLoader());

        cache.getIfPresent("tanglaoer");
        cache.getIfPresent("wanglaosan");
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("========" + cache.getIfPresent("tanglaoer"));
    }

    @Test
    public void testSoftKey() throws InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .softValues()
                .build(this.createCacheLoader());
        int i = 0;
        for (; ; ) {
            cache.put("test", new Employee("test", "test", "test"));
            System.out.println("The Employee [" + i++ + "] is store into cache.");
            TimeUnit.SECONDS.sleep(3);
        }
    }

    @Test
    public void testCacheRefresh() throws InterruptedException {
        CacheLoader<Object, Long> loader = CacheLoader.from(k -> {
            log.info("=========");
            return System.currentTimeMillis();
        });
        LoadingCache<Object, Long> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(loader);
        Long result = cache.getUnchecked("Alex");
        TimeUnit.SECONDS.sleep(3);
        Long alex = cache.getUnchecked("Alex");
        System.out.println(result == alex);
    }

    @Test
    public void testCCachePreLoad() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);
        HashMap<String, String> preData = new HashMap<>() {
            {
                put("alex", "ALEX");
                put("hello", "HELLO");
            }
        };
        cache.putAll(preData);
        System.out.println(cache.size() == 2);
    }

    @Test
    public void testCacheRemovedNotification() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        RemovalListener<String, String> listener = notification -> {
            if (notification.wasEvicted()) {

                RemovalCause cause = notification.getCause();
                log.info("evicted key: {}", notification.getKey());
                assertThat(cause, Is.is(RemovalCause.SIZE));
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);
        cache.getUnchecked("Alex");
        cache.getUnchecked("Alex2");
        cache.getUnchecked("Alex3");
        cache.getUnchecked("Alex5");
    }
}
