package cn.tanglaoer.guava.eventbus.monitor;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public interface TargetMonitor {
    void startMonitor() throws Exception;

    void stopMonitor() throws IOException;
}
