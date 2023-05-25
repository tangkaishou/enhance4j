package cn.tanglaoer.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class ImmutableListTest {
    @Test
    public void testOf() {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);  // 不可变的集合
        list.add(4); // 报错
        System.out.println(list);
    }

    @Test
    public void testCopy() {
        Integer[] array = {1, 2, 3, 4, 5};
        System.out.println(ImmutableList.copyOf(array));
    }

    @Test
    public void testBuilder() {
        ImmutableMap<String, String> map =
                ImmutableMap.<String, String>builder().put("Oracle", "12c").put("Mysql", "7.0").build();
        map.put("test", "test");

    }


}
