package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.listener.DeadEventListener;
import com.google.common.eventbus.EventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class DeadEventBusExample {
    /**
     * listener 监听器的参数类型 DeadEvent
     * @param args
     */
    public static void main(String[] args) {
        DeadEventListener deadEventListener = new DeadEventListener();
        EventBus eventBus = new EventBus("DeadEventBus"){
            @Override
            public String toString() {
                return "DEAD-EVENT-BUS";
            }
        };
        eventBus.register(deadEventListener);
        eventBus.post("hello world");
        eventBus.post("hello world");


        eventBus.unregister(deadEventListener);
        eventBus.post("hello");
    }
}
