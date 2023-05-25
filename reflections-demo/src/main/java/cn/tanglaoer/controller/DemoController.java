package cn.tanglaoer.controller;

import cn.tanglaoer.annotation.MyAnnotation;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/11
 */
@MyAnnotation(value = "demoController")
public class DemoController {
    @MyAnnotation(value = "test")
    public String test() {
        return "test";
    }
}
