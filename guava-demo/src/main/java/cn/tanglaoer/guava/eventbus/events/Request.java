package cn.tanglaoer.guava.eventbus.events;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/24
 */
public class Request {
    private String orderNo;

    public Request(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Request{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}
