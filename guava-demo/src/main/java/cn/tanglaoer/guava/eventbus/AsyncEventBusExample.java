package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.AsyncEventBus;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/26
 */
public class AsyncEventBusExample {
    public static void main(String[] args) {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(4));
        eventBus.register(new SimpleListener());
        eventBus.post("hello");
    }

    @Test
    public void test() {
        AsyncEventBus eventBus = new AsyncEventBus(Runnable::run);
        eventBus.register(new SimpleListener());
        eventBus.post("hello");
    }
}
