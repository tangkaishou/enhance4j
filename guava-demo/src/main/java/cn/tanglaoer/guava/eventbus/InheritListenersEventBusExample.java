package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.listener.ConcreteListener;
import com.google.common.eventbus.EventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class InheritListenersEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        System.out.println("post the string event");
        eventBus.post("i am string event");
        System.out.println("post the int event");
        eventBus.post(1000);
    }
}
