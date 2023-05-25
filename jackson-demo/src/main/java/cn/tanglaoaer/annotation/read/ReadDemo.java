package cn.tanglaoaer.annotation.read;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class ReadDemo {
    @Test
    public void test1() throws JsonProcessingException {
        String json = """
                {
                  "id"   : 1234,
                  "name" : "John"
                }        
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);
    }

    @Test
    public void test2() throws JsonProcessingException {
        String json = """
                {
                  "id"   : 1234,
                  "name" : "John"
                }        
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        Bag bag = objectMapper.readValue(json, Bag.class);
        System.out.println(bag.get("id"));
    }

    @Test
    public void test3() throws JsonProcessingException {
        String json = """
                {
                  "id"   : 1234,
                  "name" : "John"
                }        
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        PersonImmutable vo = objectMapper.readValue(json, PersonImmutable.class);
        System.out.println(vo);
    }

    @Test
    public void test4() throws IOException {
        InjectableValues inject = new InjectableValues.Std().addValue(String.class, "jenkov.com");
        PersonInject personInject = new ObjectMapper().reader(inject)
                .forType(PersonInject.class)
                .readValue(new File("data/person.json"));
        System.out.println(personInject);
    }

    @Test
    public void test5() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDeserialize person = objectMapper
                .reader(PersonDeserialize.class)
                .readValue(new File("data/person-optimized-boolean.json"));
        System.out.println(person);
    }
}
