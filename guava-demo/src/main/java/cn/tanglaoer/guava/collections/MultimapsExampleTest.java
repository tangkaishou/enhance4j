package cn.tanglaoer.guava.collections;

import com.google.common.collect.LinkedListMultimap;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class MultimapsExampleTest {
    @Test
    public void test() {
        LinkedListMultimap<@Nullable String, @Nullable String> multipleMap = LinkedListMultimap.create();
        multipleMap.put("1", "1");
        multipleMap.put("1", "2");
        System.out.println(multipleMap);
    }

}
