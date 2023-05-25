package cn.tanglaoaer.vo;

import java.util.Date;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class Transaction {
    private String type = null;
    private Date date = null;

    public Transaction() {
    }

    public Transaction(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
