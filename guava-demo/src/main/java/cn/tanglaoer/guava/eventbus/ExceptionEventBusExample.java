package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.listener.ExceptionListener;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class ExceptionEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus(((exception, context) -> {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        }));
        eventBus.register(new ExceptionListener());
        eventBus.post("hello");
    }


    // static class ExceptionHandler implements SubscriberExceptionHandler {
    //     @Override
    //     public void handleException(Throwable exception, SubscriberExceptionContext context) {
    //         System.out.println(context.getEvent());
    //         System.out.println(context.getEventBus());
    //         System.out.println(context.getSubscriber());
    //         System.out.println(context.getSubscriberMethod());
    //     }
    // }
}
