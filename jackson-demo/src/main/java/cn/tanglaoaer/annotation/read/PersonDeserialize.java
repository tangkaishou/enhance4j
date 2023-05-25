package cn.tanglaoaer.annotation.read;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonDeserialize {

    public long    id      = 0;
    public String  name    = null;

    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    public boolean enabled = false;

    @Override
    public String toString() {
        return "PersonDeserialize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
