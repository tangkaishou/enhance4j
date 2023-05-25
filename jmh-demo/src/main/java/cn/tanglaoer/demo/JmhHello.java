package cn.tanglaoer.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author： tks
 * @date： 2023/3/13
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class JmhHello {
    String string = "";
    StringBuilder stringBuilder = new StringBuilder();

    @Benchmark
    public String stringAdd() {
        for (int i = 0; i < 1000; i++) {
            string = string + 1;
        }
        return string;
    }

    @Benchmark
    public String stringBuilderAppend() {
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }

    /**
     * 需要有一个主方法
     * @param args
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JmhHello.class.getSimpleName()).build();
        new Runner(opt).run();
    }
}
