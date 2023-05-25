package cn.tanglaoer.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/27
 */
public class RateLimiterExample {
    /**
     * 2秒执行一次
     */
    private final static RateLimiter limiter = RateLimiter.create(0.5);

    private final static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> {
            service.submit(RateLimiterExample::testSemaphore);
        });
    }

    private static void testLimiter() {
        System.out.println(Thread.currentThread() + " waiting " + limiter.acquire());
    }

    private static void testSemaphore() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread() + " is coming and do work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread() + " release the semaphore");
        }
    }
}
