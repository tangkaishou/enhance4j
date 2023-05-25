package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
@JsonPropertyOrder({"name", "personId"})
public class PersonPropertyOrder {

    public long  personId  = 0;
    public String name     = null;

}
