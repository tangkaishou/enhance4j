package cn.tanglaoer.guava.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class DeadEventListener {

    @Subscribe
    public void handle(DeadEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.getEvent());
    }
}
