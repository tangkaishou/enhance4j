package cn.tanglaoer.strings;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 目的是处理字符串根据逗号进行分隔到Set中、这个类官方已经不建议采用
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/6/14
 */
public class StringToConllections {
    public static void main(String[] args) {
        String permIds = generateData();
        // Set<Integer> collect = Sets.newHashSetWithExpectedSize(1280);
        // StringTokenizer userPermTokenizer = new StringTokenizer(permIds, ",", false);
        // while (userPermTokenizer.hasMoreElements()) {
        //     collect.add(Integer.parseInt(userPermTokenizer.nextToken()));
        // }
        String[] split = permIds.split(",");
        Set<Integer> collect = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toSet());
        System.out.println(collect);
    }

    public static String generateData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            if (i == 1000 - 1) {
                stringBuilder.append(ThreadLocalRandom.current().nextInt(0, 1000));
            }else {
                stringBuilder.append(ThreadLocalRandom.current().nextInt(0, 1000)).append(",");
            }
        }
        return stringBuilder.toString();
    }
}
