package cn.tanglaoer.guava.utilities;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class ElapsedExample {
    public static void main(String[] args) throws InterruptedException {
        process("1123456");
    }


    private static void process(String orderNo) throws InterruptedException {
        System.out.printf("start process the order %s\n", orderNo);
        long currentTime = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo %s ", System.nanoTime() - currentTime);
    }


}
