package cn.tanglaoaer.annotation.read;

import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonInject {

    public long   id   = 0;
    public String name = null;

    @JacksonInject
    public String source = null;

    @Override
    public String toString() {
        return "PersonInject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
