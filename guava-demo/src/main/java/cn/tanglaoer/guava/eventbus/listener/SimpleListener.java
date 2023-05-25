package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class SimpleListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Subscribe
    public void doAction(final String event) {
        if (LOGGER.isInfoEnabled()) {
           LOGGER.info("Received event [{}] and will task a action", event);
        }
    }

    @Subscribe
    public void doAction1(final String event) {
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Received event [{}] and will task a action", event);
        }
    }
}
