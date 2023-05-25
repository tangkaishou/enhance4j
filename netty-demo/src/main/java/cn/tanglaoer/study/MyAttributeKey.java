package cn.tanglaoer.study;

import io.netty.util.AbstractConstant;
import io.netty.util.AttributeKey;
import io.netty.util.ConstantPool;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/16
 */
public class MyAttributeKey<T> extends AbstractConstant<MyAttributeKey<T>> {
    private static final ConstantPool<MyAttributeKey<Object>> pool = new ConstantPool<MyAttributeKey<Object>>() {
        protected MyAttributeKey<Object> newConstant(int id, String name) {
            return new MyAttributeKey(id, name);
        }
    };

    private MyAttributeKey(int id, String name) {
        super(id, name);
    }
}
