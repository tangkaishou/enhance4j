package cn.tanglaoer.guava.eventbus.listener;

import cn.tanglaoer.guava.eventbus.events.Apple;
import cn.tanglaoer.guava.eventbus.events.Fruit;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class FruitEaterListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eatFruit(Fruit fruit) {
        LOGGER.info("{}", fruit);
    }

    @Subscribe
    public void eatApple(Apple apple) {
        LOGGER.info("eat apple {}", apple);
    }
}
