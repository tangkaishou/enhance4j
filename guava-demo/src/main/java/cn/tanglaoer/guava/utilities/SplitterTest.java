package cn.tanglaoer.guava.utilities;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/22
 */
public class SplitterTest {
    @Test
    public void testSplitOnSplit() {
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result, notNullValue());
    }

    @Test
    public void testSplitOnSplitOmitEmptyTrimResult() {
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world | | " +
                "|");
        assertThat(result, notNullValue());
    }

    @Test
    public void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0), equalTo("aaaa"));
        assertThat(result.get(3), equalTo("dddd"));
    }

    @Test
    public void testSplitOnSplitLimit() {
        List<String> result = Splitter.on("#").limit(3).splitToList("hello#world#java#google" +
                "#scala");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(3));
        System.out.println(result);
    }

    @Test
    public void testSplitOnPatternString() {
        List<String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().splitToList(
                "hello | world|||");
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(2));
        System.out.println(result);
    }

    @Test
    public void testSplitOnMap() {
        Map<String, String> result =
                Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings()
                        .withKeyValueSeparator("=").split("hello=world| ttt=ddd|||");
        System.out.println(result);
    }
}
