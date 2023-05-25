package cn.tanglaoer.demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author： tks
 * @date： 2023/3/13
 */
@Slf4j
public class MyBenchmark {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
        }
    }

    @Benchmark
    public void test() {
        for (int i = 0; i < 100; i++) {
        }
    }
}
