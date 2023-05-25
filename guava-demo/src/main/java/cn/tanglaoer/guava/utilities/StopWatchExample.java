package cn.tanglaoer.guava.utilities;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class StopWatchExample {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(stopwatch.stop().elapsed(TimeUnit.MINUTES));

    }
}
