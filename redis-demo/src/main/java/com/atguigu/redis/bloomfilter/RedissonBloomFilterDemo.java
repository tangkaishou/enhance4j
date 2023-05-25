package com.atguigu.redis.bloomfilter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.IDN;
import java.util.concurrent.TimeUnit;

/**
 * @author： tks
 * @date： 2023/1/10
 */
public class RedissonBloomFilterDemo {
    public static final int _1w = 10000;

    //布隆过滤器里预计要插入多少数据
    public static int init_size = 100 * _1w;

    //误判率,它越小误判的个数也就越少
    public static double fpp = 0.01;

    public static final String HOST = "redis://localhost:6381";

    static RedissonClient redissonClient = null; // jedis客户端

    static RBloomFilter rBloomFilter = null; // redis版内置的布隆过滤器

    @Resource
    RedisTemplate redisTemplate;

    static {
        // 设置redis配置信息
        Config config = new Config();
        config.useSingleServer().setAddress(HOST).setDatabase(0);

        // 构造redission
        redissonClient = Redisson.create(config);

        // 通过redisson构造 布隆过滤器
        rBloomFilter = redissonClient.getBloomFilter("phoneListBloomFilter", new StringCodec());

        // 初始化指定 布隆过滤器(预期大小、误差率）
        rBloomFilter.tryInit(init_size, fpp);

        rBloomFilter.add("10086");

        redissonClient.getBucket("10086",new StringCodec()).set("chinamobile10086"); // redis里面添加一份
    }


    private static String getPhoneListById(String IDNumber) {
        String result = null;

        if (IDNumber == null) {
            return null;
        }

        // 1.先判断布隆过滤器中是否存在
        if (rBloomFilter.contains(IDNumber)) {
            // 2.redis中获取结果
            RBucket<String> rBucket = redissonClient.getBucket(IDNumber, new StringCodec());
            result = rBucket.get();
            if (result != null) {
                return "i come from redis: " + result;
            }else {
                result = getPhoneListByMySQL(IDNumber);
                if (result == null) {
                    return null;
                }
            }
        }

        return result;
    }

    private static String getPhoneListByMySQL(String idNumber) {
        return "chinamobile" + idNumber;
    }


    public static void main(String[] args) {
        String phoneListById = getPhoneListById("10086");
        System.out.println("------查询出来的结果： "+phoneListById);

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        redissonClient.shutdown();
    }

}
