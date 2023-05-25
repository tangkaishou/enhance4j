package cn.tanglaoer.guava.eventbus.internal;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/25
 */
public interface MyEventExceptionHandler {
    void handle(Throwable cause, MyEventContext context);
}
