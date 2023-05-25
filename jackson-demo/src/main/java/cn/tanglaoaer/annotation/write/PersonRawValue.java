package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class PersonRawValue {

    public long   personId = 0;

    @JsonRawValue
    // public String address  = "{ \"street\" : \"Wall Street\", \"no\":1}";
    public String address  = "{ \"street\" : \"Wall Street\", \"no\":1}";
}
