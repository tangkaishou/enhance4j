package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
@Slf4j
public class ExceptionListener {

    @Subscribe
    public void m1(String event) {
        log.info("==========m1========={}", event);
    }

    @Subscribe
    public void m2(String event) {
        log.info("==========m2========={}", event);
    }

    @Subscribe
    public void m3(String event) {
        throw new RuntimeException("ddd");
        // log.info("==========m3========={}", event);
    }
}
