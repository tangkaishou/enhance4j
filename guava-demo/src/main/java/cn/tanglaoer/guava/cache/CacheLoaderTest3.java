package cn.tanglaoer.guava.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/29
 */
public class CacheLoaderTest3 {

    @Test
    public void testCacheStat() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().recordStats().build(loader);
        cache.getUnchecked("alex");
        // 不可变
        CacheStats stats = cache.stats();
        System.out.println(stats.missCount());
        cache.getUnchecked("alex");
        System.out.println(cache.stats().hitCount());
    }

    @Test
    public void testCacheSpec() throws ExecutionException {
        String spec = "maximumSize=5";
        CacheBuilderSpec builderSpec = CacheBuilderSpec.parse(spec);
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.from(builderSpec).build(loader);
        System.out.println(cache);
        System.out.println(cache.get("maximumSize"));
    }
}
