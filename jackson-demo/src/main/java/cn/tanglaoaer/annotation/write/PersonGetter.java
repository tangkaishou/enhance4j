package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonGetter {

    private long  personId = 0;

    @JsonGetter("id")
    public long personId() { return this.personId; }

    @JsonSetter("id")
    public void personId(long personId) { this.personId = personId; }

}
