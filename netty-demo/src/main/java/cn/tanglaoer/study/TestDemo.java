package cn.tanglaoer.study;

import io.netty.channel.Channel;
import io.netty.util.AbstractConstant;
import io.netty.util.AttributeKey;
import io.netty.util.ConstantPool;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/16
 */
public class TestDemo {
    public static void main(String[] args) {
        AttributeKey<Object> key = AttributeKey.valueOf("mysql.session");
        System.out.println(key);

    }
}

