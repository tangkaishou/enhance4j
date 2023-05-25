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
public class RequestQueryHandler  {
    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    @Subscribe
    public void doQuery(Request request) {
        log.info("start query the orderNo [{}]", request.toString());
        Response response = new Response();
        this.eventBus.post(new Response());
    }
}
