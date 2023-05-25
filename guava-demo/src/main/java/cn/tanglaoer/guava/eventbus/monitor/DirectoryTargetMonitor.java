package cn.tanglaoer.guava.eventbus.monitor;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
@Slf4j
public class DirectoryTargetMonitor implements TargetMonitor {
    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath) {
        this(eventBus, targetPath, "");
    }

    public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath,
                                  String ...morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, "");
    }

    @Override
    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
        log.info("The directory [{}] is monitoring...", path);
        start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = this.path.resolve(path);
                    eventBus.post(new FileChangeEvent(child, kind));
                });
            } catch (InterruptedException e) {
                this.start = false;
            }finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    @Override
    public void stopMonitor() throws IOException {
        log.info("The directory [{}] monitor will be stop...", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        log.info("The directory [{}] monitor will be stop done", path);
    }
}
