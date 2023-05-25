package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.events.Apple;
import cn.tanglaoer.guava.eventbus.events.Fruit;
import cn.tanglaoer.guava.eventbus.listener.FruitEaterListener;
import com.google.common.eventbus.EventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class InheritEventsEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));
        System.out.println("====================");
        eventBus.post(new Fruit("apple"));
    }
}
