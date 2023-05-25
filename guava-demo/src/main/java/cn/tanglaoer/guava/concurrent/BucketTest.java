package cn.tanglaoer.guava.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public class BucketTest {
    public static void main(String[] args) {
        final Bucket bucket = new Bucket();
        final AtomicInteger DATA_CREATOR = new AtomicInteger();

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    int data = DATA_CREATOR.getAndIncrement();
                    bucket.submit(data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(200L);
                    } catch (Exception e) {
                        if (e instanceof IllegalStateException) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }).start();
        });
        // 25:10= 5:2
        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            for(;;) {
                bucket.takeThenConsumer(x -> System.out.printf(Thread.currentThread() + " W " + x));
            }
        }).start());

    }
}
