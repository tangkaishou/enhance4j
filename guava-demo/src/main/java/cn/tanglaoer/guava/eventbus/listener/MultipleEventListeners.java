package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class MultipleEventListeners {
    private final static Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

    @Subscribe
    public void task1(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task1==", event);
        }
    }


    @Subscribe
    public void task2(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==task2==", event);
        }
    }


    /**
     * 注意这里不能写int、只能是Integer
     * @param event
     */
    @Subscribe
    public void intTask(Integer event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] received and will take a action by ==intTask==", event);
        }
    }

}
