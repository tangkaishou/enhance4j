package cn.tanglaoer.guava.utilities;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/22
 */
public class StringsTest {
    @Test
    public void testStringsMethod() {
        System.out.println(Strings.emptyToNull(""));
        System.out.println(Strings.emptyToNull("hhh"));
        System.out.println(Strings.nullToEmpty(null).equals(""));

        // 公共前缀
        System.out.println(Strings.commonPrefix("Hello", "Hit").equals("H"));
        System.out.println(Strings.commonPrefix("Hello", "Eit").equals(""));
        System.out.println(Strings.commonSuffix("Hello", "Echo").equals("o"));


        assertThat(Strings.repeat("tang", 3), equalTo("tangtangtang"));
        assertThat(Strings.isNullOrEmpty(null), equalTo(true));
        assertThat(Strings.isNullOrEmpty(""), equalTo(true));

        // 填充
        assertThat(Strings.padStart("Alex", 3, 'H'), equalTo("Alex"));
        assertThat(Strings.padStart("Alex", 5, 'H'), equalTo("HAlex"));
        assertThat(Strings.padEnd("Alex", 5, 'H'), equalTo("AlexH"));
    }

    @Test
    public void testCharsets() {
    }

    @Test
    public void testCharMatcher() {
        boolean matches = CharMatcher.javaDigit().matches('5');
        System.out.println(matches);
        // 统计字符出现次数
        System.out.println(CharMatcher.is('A').countIn("Alex Sharding the Google Guava to Us"));
        System.out.println(CharMatcher.breakingWhitespace().collapseFrom("    hello Guava       ", '*').equals(
                "*hello*Guava*"));
        System.out.println(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"));
        System.out.println(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello" +
                " 234 world 123"));
    }
}
