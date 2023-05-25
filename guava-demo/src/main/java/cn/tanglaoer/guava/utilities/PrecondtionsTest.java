package cn.tanglaoer.guava.utilities;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/22
 */
public class PrecondtionsTest {
    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    private void checkNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            checkNotNullWithMessage(null);
        }catch (Exception e) {
        }
    }

    private void checkNotNullWithMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null");
    }
}
