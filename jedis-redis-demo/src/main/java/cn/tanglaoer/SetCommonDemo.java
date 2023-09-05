package cn.tanglaoer;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;
import java.util.Random;

/**
 * set数据结构的用法
 *
 */
public class SetCommonDemo {

    /**
     * 创建数据
     */
    @Test
    public void createData() {
        Jedis jedis = RedisUtils.getJedis();
        for (int i = 0; i < 20; i++) {
            jedis.sadd("random", RandomUtil.randomString(3));
        }
    }

    /**
     * 增量遍历（很重要）生产环境都是用这个命令来遍历
     */
    @Test
    public void sscan() {
        Jedis jedis = RedisUtils.getJedis();
        String cursor = "0";
        while (true) {
            // KTN
            ScanResult<String> word = jedis.sscan("random", cursor, new ScanParams().count(30).match("F*"));
            System.out.println(word.getCursor());
            cursor = word.getCursor();
            List<String> result = word.getResult();
            System.out.println(result);
            if (cursor.equals("0")) {
                break;
            }
        }
    }

    @Test
    public void sismember() {

    }
}
