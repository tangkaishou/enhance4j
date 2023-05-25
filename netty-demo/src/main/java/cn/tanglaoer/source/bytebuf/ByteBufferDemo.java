package cn.tanglaoer.source.bytebuf;

import java.nio.ByteBuffer;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/14
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(88);
        String value = "Netty权威指南";
        buffer.put(value.getBytes());
        buffer.flip();
        byte[] vArray = new byte[buffer.remaining()];
        buffer.get(vArray);
        String s = new String(vArray);
        System.out.println(s);
    }
}
