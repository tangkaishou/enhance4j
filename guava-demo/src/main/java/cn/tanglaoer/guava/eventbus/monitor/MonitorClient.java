package cn.tanglaoer.guava.eventbus.monitor;

import com.google.common.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class MonitorClient {
    public static void main(String[] args) throws Exception {
        EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "F:\\demo\\guava-demo\\monitor");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                monitor.stopMonitor();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 5, TimeUnit.SECONDS);
        executorService.shutdown();
        monitor.startMonitor();
    }
}
