package cn.tanglaoer.guava.eventbus.monitor;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
@Slf4j
public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event) {
        log.info("{}-{}", event.getPath(), event.getKind());
    }
}
