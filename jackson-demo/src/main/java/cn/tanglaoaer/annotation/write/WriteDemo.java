package cn.tanglaoaer.annotation.write;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class WriteDemo {
    @Test
    public void test1() throws JsonProcessingException {
        PersonInclude personInclude = new PersonInclude();

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(personInclude);
        System.out.println(s);
    }

    @Test
    public void test2() throws IOException {
        PersonAnyGetter personAnyGetter = new PersonAnyGetter();
        personAnyGetter.properties().put("hello", "world");
        personAnyGetter.properties().put("test", "test");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/person-any-getter.json"), personAnyGetter);
        System.out.println(personAnyGetter);
    }

    @Test
    public void test3() throws IOException {
        PersonPropertyOrder order = new PersonPropertyOrder();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/order.json"), order);
        System.out.println(order);
    }

    @Test
    public void test4() throws IOException {
        PersonRawValue raw = new PersonRawValue();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/raw.json"), raw);
        System.out.println(raw);
    }

    @Test
    public void test5() throws IOException {
        PersonValue personValue = new PersonValue();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/value.json"), personValue);
        System.out.println(personValue);
    }

    @Test
    public void test6() throws IOException {
        PersonSerializer serializer = new PersonSerializer();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("data/serializer.json"), serializer);
        System.out.println(serializer);
    }
}
