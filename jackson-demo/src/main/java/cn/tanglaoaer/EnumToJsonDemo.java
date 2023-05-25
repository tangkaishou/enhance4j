package cn.tanglaoaer;

import cn.tanglaoaer.vo.UserType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/18
 */
public class EnumToJsonDemo {
    public static void main(String[] args) throws JsonProcessingException {
        MyTestUserType myTestUserType = new MyTestUserType();
        myTestUserType.setUserType(UserType.GUEST);
        myTestUserType.setName("hello world");

        System.out.println("-- before serialization --");
        System.out.println(myTestUserType);

        System.out.println("-- after serialization --");
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(myTestUserType);
        System.out.println(jsonString);
    }
}


class MyTestUserType {
    private UserType userType;

    private String name;

    public MyTestUserType() {
    }

    public MyTestUserType(UserType userType, String name) {
        this.userType = userType;
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyTestUserType{" +
                "userType=" + userType +
                ", name='" + name + '\'' +
                '}';
    }
}
