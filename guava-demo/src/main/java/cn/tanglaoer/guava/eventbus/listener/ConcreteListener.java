package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class ConcreteListener extends BaseListener{
    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteListener.class);

    @Subscribe
    public void conTask(String event) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("the event [{}] will be handle by {}. {}",
                    this.getClass().getSimpleName(), "conTask");
        }
    }
}
