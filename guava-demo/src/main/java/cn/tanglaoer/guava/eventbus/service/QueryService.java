package cn.tanglaoer.guava.eventbus.service;

import cn.tanglaoer.guava.eventbus.events.Request;
import cn.tanglaoer.guava.eventbus.events.Response;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
@Slf4j
public class QueryService {
    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    public void query(String orderNo) {
        log.info("Received the orderNo [{}]", orderNo);
        this.eventBus.post(new Request(orderNo));
    }

    @Subscribe
    public void handleResponse(Response response) {
        log.info("{}", response);
    }

}
