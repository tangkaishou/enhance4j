package cn.tanglaoer.guava.collections;

import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class RangeExample {

    @Test
    public void test() {
        Range<Integer> closed = Range.closed(0, 9);
        System.out.println(closed.contains(5));
        System.out.println(closed.contains(0));
        System.out.println(closed.contains(9));
        System.out.println(closed.lowerEndpoint());
    }

    @Test
    public void testOpenRange() {
        Range<Integer> closedRange = Range.open(0, 9);
        System.out.println(closedRange.contains(0)); // 不包含0
        System.out.println(closedRange.contains(5)); // 不包含0
        System.out.println(closedRange.contains(9)); // 不包含0
    }

    /**
     * {x|x>a}
     */
    @Test
    public void testGreatThan() {
        Range<Integer> range = Range.greaterThan(10);
        System.out.println(range.contains(10));
        System.out.println(range.contains(Integer.MAX_VALUE));
    }

    @Test
    public void testMapRange() {
        TreeMap<String, @Nullable Integer> treeMap = Maps.newTreeMap();
        treeMap.put("Scala", 1);
        treeMap.put("Guava", 2);
        treeMap.put("Java", 3);
        treeMap.put("Kafka", 4);
        System.out.println(treeMap);

        NavigableMap<String, @Nullable Integer> result = Maps.subMap(treeMap, Range.closed("Guava", "Java"));
        System.out.println(result);
    }
}
