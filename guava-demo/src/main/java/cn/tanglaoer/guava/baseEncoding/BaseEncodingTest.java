package cn.tanglaoer.guava.baseEncoding;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/23
 */
public class BaseEncodingTest {

    @Test
    public void testBase64Encode() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
    }

    @Test
    public void testBase64decode() {

    }
}
