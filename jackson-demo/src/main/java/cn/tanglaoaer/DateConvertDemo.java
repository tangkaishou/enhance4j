package cn.tanglaoaer;

import cn.tanglaoaer.vo.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转化
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class DateConvertDemo {

    @Test
    public void dateToLong() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        String output = objectMapper.writeValueAsString(transaction);

        System.out.println(output);
    }

    @Test
    public void dateToString() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);

        String output2 = objectMapper.writeValueAsString(transaction);
        System.out.println(output2);
    }
}
