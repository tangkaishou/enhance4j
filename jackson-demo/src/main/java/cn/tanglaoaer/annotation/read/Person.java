package cn.tanglaoaer.annotation.read;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class Person {
    private long   personId = 0;

    private String name     = null;

    public long getPersonId() { return this.personId; }

    @JsonSetter("id")
    public void setPersonId(long personId) { this.personId = personId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}
