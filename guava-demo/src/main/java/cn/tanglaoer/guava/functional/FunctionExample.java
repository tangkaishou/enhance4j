package cn.tanglaoer.guava.functional;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/22
 */
public class FunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> function = new Function<>() {
            @Override
            public Integer apply(String input) {
                Preconditions.checkNotNull(input, "The input Stream should not be null.");
                return input.length();
            }
        };
        System.out.println(function.apply("Hello"));

    }
}
