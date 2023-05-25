package cn.tanglaoaer.annotation.readwrite;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class PersonAutoDetect {

    private long  personId = 123;

    public String name     = null;
}
