package cn.tanglaoer.guava.concurrent;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * 漏桶算法
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/27
 */
public class Bucket {
    private final ConcurrentLinkedQueue<Integer> container = new ConcurrentLinkedQueue<>();

    /**
     * 桶的上流为1000
     */
    private final static int BUCKET_LIMIT = 1000;

    /**
     * 每次处理10个
     */
    private final RateLimiter limiter = RateLimiter.create(10);

    private final Monitor offerMonitor = new Monitor();

    private final Monitor pollMonitor = new Monitor();

    public void submit(Integer data) {
        if (offerMonitor.enterIf(offerMonitor.newGuard(() -> container.size() < BUCKET_LIMIT))) {
            try {
                container.offer(data);
                System.out.println(Thread.currentThread() + " submit data " + data + ",current " +
                        "size " + container.size());
            }finally {
                offerMonitor.leave();
            }
        }else {
            throw new IllegalStateException("The bucket is full.");
        }
    }

    public void takeThenConsumer(Consumer<Integer> consumer) {
        if (pollMonitor.enterIf(pollMonitor.newGuard(() -> !container.isEmpty()))) {
            try {
                System.out.println(Thread.currentThread() + " waiting " + limiter.acquire());
                consumer.accept(container.poll());
            }finally {
                pollMonitor.leave();
            }
        }
    }
}
