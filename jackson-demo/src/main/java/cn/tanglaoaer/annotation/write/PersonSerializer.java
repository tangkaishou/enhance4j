package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonSerializer {

    public long   personId = 0;
    public String name     = "John";

    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    public boolean enabled = false;
}
