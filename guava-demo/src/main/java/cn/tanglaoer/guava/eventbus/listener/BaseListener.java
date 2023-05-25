package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class BaseListener extends AbstractListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractListener.class);


    @Subscribe
    public void baseTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] will be handle by {}. {}",
                    this.getClass().getSimpleName(), "commonTask");
        }
    }
}
