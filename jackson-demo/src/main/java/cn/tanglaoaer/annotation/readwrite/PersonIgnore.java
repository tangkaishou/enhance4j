package cn.tanglaoaer.annotation.readwrite;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonIgnore {
    @JsonIgnore
    public long personId = 0;

    public String name = null;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
