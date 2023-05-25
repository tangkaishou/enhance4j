package cn.tanglaoaer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户 类型。
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserType {
    ANONYMOUS(-1, "匿名用户"),

    GUEST(0, "B站用户");

    private int value;

    private String label;

    UserType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
