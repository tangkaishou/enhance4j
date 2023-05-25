package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonValue {

    public long personId = 0;
    public String name = null;

    @JsonValue
    public String toJson() {
        return this.personId + "," + this.name;
    }

}
