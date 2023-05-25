package cn.tanglaoaer.annotation.readwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonIgnoreDemo {
    @Test
    public void test() throws JsonProcessingException {
        PersonIgnore person = new PersonIgnore();
        person.setName("hello");
        person.setPersonId(10);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(person));
    }

    @Test
    public void test1() throws JsonProcessingException {
        PersonIgnoreProperties personIgnoreProperties = new PersonIgnoreProperties();
        personIgnoreProperties.setPersonId(1000);
        personIgnoreProperties.setFirstName("hello");
        personIgnoreProperties.setLastName("world");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(personIgnoreProperties));
    }

    @Test
    public void test2() throws JsonProcessingException {
        PersonIgnoreType personIgnoreType = new PersonIgnoreType();
        personIgnoreType.setPersonId(1000);
        personIgnoreType.setName("hello");
        personIgnoreType.setAddress(new PersonIgnoreType.Address("1", "2", "3", "4", "5"));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(personIgnoreType));
    }

    @Test
    public void test3() throws JsonProcessingException {
        PersonAutoDetect personAutoDetect = new PersonAutoDetect();
        personAutoDetect.name = "hello";

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(personAutoDetect));
    }
}
