package cn.tanglaoaer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonNodeDemo {

    @Test
    public void objectNodeDemo() throws JsonProcessingException {
        String json = "{ \"f1\" : \"v1\" } ";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        System.out.println(jsonNode.get("f1").asText());
    }

    /**
     * JsonNode -> JSON
     * @throws JsonProcessingException
     */
    @Test
    public void test2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"f1\" : \"v1\" } ";
        JsonNode jsonNode = objectMapper.readTree(json);

        String json1 = objectMapper.writeValueAsString(jsonNode);
        System.out.println(json1);
    }

    /**
     * 获取JsonNode字段
     * @throws JsonProcessingException
     */
    @Test
    public void test3() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"f1\" : \"v1\" } ";
        JsonNode jsonNode = objectMapper.readTree(json);
        // 返回值还是JsonNode
        JsonNode field1 = jsonNode.get("f1");
        System.out.println(field1);

        // 需要转换为基本类型
        String s = field1.asText();
        System.out.println(s);
    }

    @Test
    public void testAt() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                {
                  "identification" :  {
                        "name" : "James",
                        "ssn": "ABC123552"
                    }
                }""";
        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode nameNode = jsonNode.at("/identification/name");
        System.out.println(nameNode);
    }


    @Test
    public void createObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
    }


    @Test
    public void setObjectNode () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode parentNode = objectMapper.createObjectNode();
        String json = """
                {
                  "identification" :  {
                        "name" : "James",
                        "ssn": "ABC123552"
                    }
                }""";
        JsonNode childNode = objectMapper.readTree(json);
        parentNode.set("child1", childNode);
        System.out.println(parentNode);
    }

    @Test
    public void putObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("field1", "value1");
        objectNode.put("field2", 123);
        objectNode.put("field3", 999.999);
        System.out.println(objectNode);
    }

    @Test
    public void removeJsonNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("field1", "value1");
        objectNode.put("field2", 123);
        objectNode.put("field3", 999.999);
        System.out.println(objectNode);

        // 删除字段
        objectNode.remove("field3");
        System.out.println(objectNode);
    }

    /**
     * 遍历JsonNode
     */
    @Test
    public void iterJsonNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("field1", "value1");
        objectNode.put("field2", 123);
        objectNode.put("field3", 999.999);
        Iterator<String> fieldNames = objectNode.fieldNames();

        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode field = objectNode.get(fieldName);
            System.out.println(field);
        }
        System.out.println("hello");
    }
}
