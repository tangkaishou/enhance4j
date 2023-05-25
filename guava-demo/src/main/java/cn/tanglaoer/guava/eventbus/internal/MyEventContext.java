package cn.tanglaoer.guava.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/25
 */
public interface MyEventContext {
    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
