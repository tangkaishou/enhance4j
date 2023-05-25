package cn.tanglaoer.guava.collections;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.Temperature;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/30
 */
public class FluentIterableExampleTest {
    public static void main(String[] args) {
    }

    private FluentIterable<String> build() {
        ArrayList<String> list = Lists.newArrayList("Alex", "Wang", "Guava", "Scala");
        return FluentIterable.from(list);
    }

    @Test
    public void test() {
        FluentIterable<String> fit = build();
        System.out.println(fit.filter(e -> e != null && e.length() > 4));
    }

    @Test
    public void testAppend() {
        FluentIterable<String> fit = build();
        ArrayList<String> append = Lists.newArrayList("APPEND");
        FluentIterable<String> afit = fit.append(append);
        System.out.println(afit);
    }

    @Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        boolean b = fit.allMatch(e -> e != null && e.length() >= 4);
    }
}
