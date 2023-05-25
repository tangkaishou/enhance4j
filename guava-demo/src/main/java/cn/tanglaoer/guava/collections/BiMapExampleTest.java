package cn.tanglaoer.guava.collections;

import com.google.common.collect.HashBiMap;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class BiMapExampleTest {
    @Test
    public void test() {
        HashBiMap<@Nullable Object, @Nullable Object> bimap = HashBiMap.create();
        bimap.put("1", "2");
        bimap.put("2", "2");
    }
}
