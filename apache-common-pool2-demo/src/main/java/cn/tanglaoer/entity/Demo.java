package cn.tanglaoer.entity;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: tangkaishou
 * @date: 2023/5/31 18:11
 */
public class Demo {
    private String name;

    public Demo(String name) throws InterruptedException {
        long start = System.currentTimeMillis();

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        Thread.sleep(4000 + tlr.nextInt(2000));
        long end = System.currentTimeMillis();
        System.out.println(name + "创建耗时:" + (end-start) + "ms");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class SimplePooledObjectFactory extends BasePooledObjectFactory<Demo> {
    @Override
    public Demo create() throws Exception {
        String name = "test" + ThreadLocalRandom.current().nextInt(100);
        return new Demo(name);
    }

    @Override
    public PooledObject<Demo> wrap(Demo obj) {
        return new DefaultPooledObject<>(obj);
    }
}

class DemoPooledObjectFactory implements PooledObjectFactory<Demo> {
    @Override
    public void activateObject(PooledObject<Demo> p) throws Exception {

    }

    @Override
    public void destroyObject(PooledObject<Demo> p) throws Exception {

    }

    @Override
    public PooledObject<Demo> makeObject() throws Exception {
        String name = "test" + ThreadLocalRandom.current().nextInt(100);
        return new DefaultPooledObject<>(new Demo(name));
    }

    @Override
    public void passivateObject(PooledObject<Demo> p) throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<Demo> p) {
        return true;
    }
}

class Test {
    public static void main(String[] args) {
        GenericObjectPoolConfig<Demo> config = config();
        DemoPooledObjectFactory objectFactory = new DemoPooledObjectFactory();
        GenericObjectPool<Demo> objectPool = new GenericObjectPool<>(objectFactory, config);

    }

    public static GenericObjectPoolConfig<Demo> config () {
        // 创建配置对象
        GenericObjectPoolConfig<Demo> poolConfig = new GenericObjectPoolConfig<>();
        // 最大空闲实例数，空闲超过此值将会被销毁淘汰
        poolConfig.setMaxIdle(5);
        // 最大对象数量，包含借出去的和空闲的
        poolConfig.setMaxTotal(20);
        // 最小空闲实例数，对象池将至少保留2个空闲对象
        poolConfig.setMinIdle(2);
        // 对象池满了，是否阻塞获取（false则借不到直接抛异常）
        poolConfig.setBlockWhenExhausted(true);
        // BlockWhenExhausted为true时生效，对象池满了阻塞获取超时，不设置则阻塞获取不超时，也可在borrowObject方法传递第二个参数指定本次的超时时间
        poolConfig.setMaxWaitMillis(3000);
        // 创建对象后是否验证对象，调用objectFactory#validateObject
        poolConfig.setTestOnCreate(false);
        // 借用对象后是否验证对象 validateObject
        poolConfig.setTestOnBorrow(true);
        // 归还对象后是否验证对象 validateObject
        poolConfig.setTestOnReturn(true);
        // 每30秒定时检查淘汰多余的对象, 启用单独的线程处理
        poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30);
        // 每30秒定时检查期间是否验证对象 validateObject
        poolConfig.setTestWhileIdle(false);
        // jmx监控，和springboot自带的jmx冲突，可以选择关闭此配置或关闭springboot的jmx配置
        poolConfig.setJmxEnabled(false);
        return poolConfig;
    }
}