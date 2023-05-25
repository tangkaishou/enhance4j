package cn.tanglaoaer;

import cn.tanglaoaer.vo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Jackson Tree Mode 简单例子
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonTreeModel {

    @Test
    public void treeSample() {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);
            System.out.println(jsonNode);
            System.out.println(jsonNode.get("brand"));
            System.out.println(jsonNode.get("doors"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTree() {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(carJson);
            System.out.println(jsonNode);
            System.out.println(jsonNode.get("brand"));
            System.out.println(jsonNode.get("doors"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读嵌套JSON
     */
    @Test
    public void readNestedJsonNode() {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                        "  \"nestedObject\" : { \"field\" : \"value\" } }";

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

            JsonNode brandNode = jsonNode.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            JsonNode doorsNode = jsonNode.get("doors");
            int doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            // 处理数组
            JsonNode array = jsonNode.get("owners");
            JsonNode childNode = array.get(0);
            String john = childNode.asText();
            System.out.println("john  = " + john);

            // 处理嵌套对象
            JsonNode child = jsonNode.get("nestedObject");
            JsonNode childField = child.get("field");
            String field = childField.asText();
            System.out.println("field = " + field);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java对象->JsonNode
     */
    @Test
    public void javaToJsonNode() {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("Cadillac");
        car.setDoors(4);

        JsonNode carJsonNode = objectMapper.valueToTree(car);
        System.out.println(carJsonNode.get("brand"));
        System.out.println(carJsonNode.get("doors"));
    }

    @Test
    public void jsonNodeTOJava() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonNode carJsonNode = objectMapper.readTree(carJson);

        // 不建议这样做
        // Car car = objectMapper.treeToValue(carJsonNode, Car.class);

        // 推荐、直接字符串转Class
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }
}
