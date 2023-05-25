package org.example.demo;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/17
 */
public class Demo1 {
    @Test
    public void testUser() {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                // 初始数量
                .initialCapacity(10)
                // 最大条数
                .maximumSize(10)
                // PS：expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
                // 最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                // 最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                // 监听缓存被移除
                .removalListener((key, val, removalCause) -> {
                })
                // 记录命中
                .recordStats()
                .build();

        cache.put("1", "张三");
        System.out.println(cache.getIfPresent("1"));
        System.out.println(cache.get("2", o -> "默认值"));
    }

    @Test
    public void autoCreateCache() {
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                // 创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10)
                // 根据key查询数据库里面的值
                .build(key -> new Date().toString());
    }

    @Test
    public void test2() {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                // 创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                // 根据key查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

        // 异步缓存返回的是CompletableFuture
        CompletableFuture<String> future = asyncLoadingCache.get("1");
        future.thenAccept(System.out::println);
    }


    @Test
    public void recordHit() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //开启记录缓存命中率等信息
                .recordStats()
                //根据key查询数据库里面的值
                .build(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });


        cache.put("1", "小明");
        cache.get("1");

        /*
         * hitCount :命中的次数
         * missCount:未命中次数
         * requestCount:请求次数
         * hitRate:命中率
         * missRate:丢失率
         * loadSuccessCount:成功加载新值的次数
         * loadExceptionCount:失败加载新值的次数
         * totalLoadCount:总条数
         * loadExceptionRate:失败加载新值的比率
         * totalLoadTime:全部加载时间
         * evictionCount:丢失的条数
         */
        System.out.println(cache.stats());
    }

    private static int NUM = 0;
    @Test
    public void test4() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                //模拟获取数据，每次获取就自增1
                .build(integer -> ++NUM);

        //获取ID=1的值，由于缓存里还没有，所以会自动放入缓存
        System.out.println(cache.get(1));// 1

        // 延迟2秒后，理论上自动刷新缓存后取到的值是2
        // 但其实不是，值还是1，因为refreshAfterWrite并不是设置了n秒后重新获取就会自动刷新
        // 而是x秒后&&第二次调用getIfPresent的时候才会被动刷新
        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1));// 1

        //此时才会刷新缓存，而第一次拿到的还是旧值
        System.out.println(cache.getIfPresent(1));// 2
    }
}
