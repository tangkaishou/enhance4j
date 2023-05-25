package cn.tanglaoer.guava.eventbus.test;

import cn.tanglaoer.guava.eventbus.internal.MySubscribe;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/26
 */
public class MySimpleListener {
    @MySubscribe
    public void test1(String x) {
        System.out.println("MySimpleListener===test1===" + x);
    }

    @MySubscribe(topic = "alex-topic")
    public void test2(Integer x) {
        System.out.println("MySimpleListener===test2===" + x);
    }
}
