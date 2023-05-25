package cn.tanglaoer.guava.eventbus.test;

import cn.tanglaoer.guava.eventbus.internal.MyAsyncEventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/26
 */
public class MyAsyncBusExample {
    public static void main(String[] args) {
        MyAsyncEventBus eventBus = new MyAsyncEventBus((ThreadPoolExecutor) Executors.newFixedThreadPool(4));
        eventBus.register(new MySimpleListener());
        eventBus.register(new MySimpleListener2());
        eventBus.post(123131, "test-topic");
        eventBus.post("hellos");
    }
}
