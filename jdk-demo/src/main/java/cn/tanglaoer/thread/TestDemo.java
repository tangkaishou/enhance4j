package cn.tanglaoer.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/31
 */
public class TestDemo {

    /**
     * 是否可以同时执行三个任务、答案是可以的、起初理解后面的任务会替换掉前面设置的job
     * 其实是所有设置的job都会执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2,
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("task-%d").build());
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("打印hello world"), 0, 3,
                TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("打印wocao"), 0, 3,
                TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("打印 wo cao wo cao "), 0, 3,
                TimeUnit.SECONDS);
        while (true) {
            Thread.sleep(10 * 1000);
        }
    }

}
