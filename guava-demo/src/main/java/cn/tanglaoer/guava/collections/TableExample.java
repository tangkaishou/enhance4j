package cn.tanglaoer.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Range;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeMap;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class TableExample {
    @Test
    public void test() {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("Language", "Java", "1.8");
        table.put("Language", "Scala", "2.3");
        table.put("Database", "Oracle", "1.8");
        System.out.println(table);

        Map<String, String> language = table.row("Language");
        System.out.println(language.containsKey("Java"));
        System.out.println(language.containsValue("2.3"));

        Map<String, String> result = table.column("Java");
        System.out.println(result);

        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        System.out.println(cells);

    }

    @Test
    public void testRangeMap() {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(0, 60), "F");
        rangeMap.put(Range.closed(60, 70), "D");
        rangeMap.put(Range.closed(71, 80), "C");
        rangeMap.put(Range.closed(81, 90), "B");
        rangeMap.put(Range.closed(91, 100), "A");
        System.out.println(rangeMap.get(80));
    }
}
