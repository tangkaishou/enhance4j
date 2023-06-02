package cn.tanglaoer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.util.Date;

/**
 * @author: tangkaishou
 * @date: 2023/6/2 18:29
 */
public class TestDemo {
    /**
     * kyro缓存。
     */
    private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            kryo.register(TaskData.class);
            kryo.register(Date.class);
            return kryo;
        }
    };

    /**
     * output缓存对象。限定缓存8k，最大8M。
     * 线程内使用，因为uw-task执行的特殊机制，可以减少内存复制。
     */
    private static final ThreadLocal<Output> outputLocal =
            ThreadLocal.withInitial(() -> new Output(8 * 1024, 16 * 1024 * 1024));

    public static void main(String[] args) {
        byte[] bytes;
        TaskData objectToConvert = new TaskData();
        objectToConvert.setName("tangkaishou");
        //序列化操作
        Kryo kryo = kryoLocal.get();
        Output output = outputLocal.get();
        kryo.writeClassAndObject(output, objectToConvert);
        output.flush();
        //此时复制出数据
        bytes = output.toBytes();
        System.out.println(bytes.length);
        System.out.println(new String(bytes).length());
        //重置output
        output.reset();
    }
}

class TaskData {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
