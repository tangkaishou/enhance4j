package cn.tanglaoer.guava.eventbus.test;

import cn.tanglaoer.guava.eventbus.internal.MyEventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/26
 */
public class MyEventBusExample {
    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus((cause, context) -> {
            cause.printStackTrace();
            System.out.println("===============");
            System.out.println(context.getSource());
            System.out.println(context.getEvent());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscribe());
        });
        myEventBus.register(new MySimpleListener());
        myEventBus.register(new MySimpleListener2());
        myEventBus.post(123131, "alex-topic");
        myEventBus.post(123131, "test-topic");
    }
}
