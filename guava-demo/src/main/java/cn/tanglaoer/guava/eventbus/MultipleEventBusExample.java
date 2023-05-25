package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.listener.MultipleEventListeners;
import cn.tanglaoer.guava.eventbus.listener.SimpleListener;
import com.google.common.eventbus.EventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class MultipleEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the simple event.");
        eventBus.post("i am String event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
