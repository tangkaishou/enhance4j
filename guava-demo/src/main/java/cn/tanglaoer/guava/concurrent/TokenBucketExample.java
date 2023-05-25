package cn.tanglaoer.guava.concurrent;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/4/28
 */
public class TokenBucketExample {
    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 110; i++) {
            new Thread(tokenBucket::buy).start();
        }
    }
}
