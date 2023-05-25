package cn.tanglaoer.guava.eventbus.internal;

import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/25
 */
public class MySubscriber {
    private final Object subscribeObject;

    private final Method subScribeMethod;

    private boolean disable;

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public MySubscriber(Object subscribeObject, Method subScribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subScribeMethod = subScribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public Method getSubScribeMethod() {
        return subScribeMethod;
    }
}
