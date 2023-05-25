package cn.tanglaoer.guava.eventbus;

import cn.tanglaoer.guava.eventbus.service.QueryService;
import cn.tanglaoer.guava.eventbus.service.RequestQueryHandler;
import com.google.common.eventbus.EventBus;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class ComEachOtherEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        eventBus.register(new RequestQueryHandler(eventBus));
        queryService.query("helloworld");
    }
}
