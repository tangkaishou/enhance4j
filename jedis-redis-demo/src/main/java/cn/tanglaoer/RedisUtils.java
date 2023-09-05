package cn.tanglaoer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/9/6
 */
public class RedisUtils {
    public static final String HOST = "192.168.56.101";

    public static final int PORT = 6381;

    public static Jedis getJedis() {
        try(JedisPool pool = new JedisPool(HOST, PORT)) {
            return pool.getResource();
        }
    }
}
