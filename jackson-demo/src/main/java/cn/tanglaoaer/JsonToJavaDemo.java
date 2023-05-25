package cn.tanglaoaer;

import cn.tanglaoaer.serializer.CarDeserializer;
import cn.tanglaoaer.vo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonToJavaDemo {
    /**
     * Json字符串->Java对象
     */
    @Test
    public void jsonToClass() {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson ="{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        try {
            Car car = objectMapper.readValue(carJson, Car.class);
            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JSON字符输入流->Java对象
     * @throws IOException
     */
    @Test
    public void readerToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        Reader reader = new StringReader(carJson);
        Car car = objectMapper.readValue(reader, Car.class);
        System.out.println(car);
    }

    /**
     * JSON文件->Java对象
     * @throws IOException
     */
    @Test
    public void fileToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("data/car.json");
        Car car = objectMapper.readValue(file, Car.class);
        System.out.println(car);
    }

    /**
     * JSON文件->Java对象
     * @throws IOException
     */
    @Test
    public void urlToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("file:data/car.json");
        Car car = objectMapper.readValue(url, Car.class);
        System.out.println(car);
    }

    /**
     * JSON字节输入流-->Java对象
     * @throws IOException
     */
    @Test
    public void fileInputStreamToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream input = new FileInputStream("data/car.json");

        Car car = objectMapper.readValue(input, Car.class);
        System.out.println(car);
    }

    /**
     * JSON二进制数组
     * @throws IOException
     */
    @Test
    public void byteToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        byte[] bytes = carJson.getBytes("UTF-8");
        Car car = objectMapper.readValue(bytes, Car.class);
        System.out.println(car);
    }

    /**
     * JSON数组字符串-->Java对象数组
     * @throws JsonProcessingException
     */
    @Test
    public void byteArrayToClass() throws JsonProcessingException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Car[] cars2 = objectMapper.readValue(jsonArray, Car[].class);
        System.out.println(Arrays.asList(cars2));
    }

    /**
     * JSON数组字符串-->List
     * @throws JsonProcessingException
     */
    @Test
    public void byteArrayToListClass() throws JsonProcessingException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>(){});
        System.out.println(cars1);
    }

    /**
     * JSON字符串-->Map
     * @throws JsonProcessingException
     */
    @Test
    public void jsonToMap() throws JsonProcessingException {
        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String,Object>>(){});
        System.out.println(jsonMap);
    }

    /**
     * 报错,不能识别多余的字段
     */
    @Test
    public void FailOnUnknownField() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson ="{ \"brand\" : \"Mercedes\", \"doors\" : 5, \"extend\": \"ignore\"}";

        try {
            Car car = objectMapper.readValue(carJson, Car.class);
            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 忽略未知的JSON字段
     */
    @Test
    public void ignoreField() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String carJson ="{ \"brand\" : \"Mercedes\", \"doors\" : 5, \"extend\": \"ignore\"}";

        try {
            Car car = objectMapper.readValue(carJson, Car.class);
            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不允许基本类型为null
     * 请注意，doors字段值为null。 Java中的基本数据类型不能为null值。
     * 默认情况下，Jackson ObjectMapper会忽略原始字段的空值。 但是，可以将Jackson ObjectMapper配置设置为失败。
     * 在FAIL_ON_NULL_FOR_PRIMITIVES配置值设置为true的情况下，尝试将空JSON字段解析为基本类型Java字段时会遇到异常。
     * 这是一个Java Jackson ObjectMapper示例，该示例将失败，因为JSON字段包含原始Java字段的空值：
     */
    @Test
    public void nullField() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        String carJson = "{ \"brand\":\"Toyota\", \"doors\":null }";
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }

    /**
     * 自定义反序列化
     */
    @Test
    public void customMapper() throws JsonProcessingException {
        String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";

        SimpleModule module =
                new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(Car.class, new CarDeserializer(Car.class));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        Car car = mapper.readValue(json, Car.class);
        System.out.println(car);
    }

}
