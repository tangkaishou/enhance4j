package org.example.demo;

import org.junit.Test;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/17
 */
public class TestDemo {
    @Test
    public void test() throws IOException {
        MessageBufferPacker messageBufferPacker = MessagePack.newDefaultBufferPacker();
        byte[] tests = new byte[]{1, 2,3,};
        MessagePacker messagePacker = messageBufferPacker.writePayload(tests);
        System.out.println(messagePacker);
    }
}
