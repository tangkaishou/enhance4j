package cn.tanglaoaer;

import cn.tanglaoaer.serializer.CarSerializer;
import cn.tanglaoaer.vo.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 1.writeValue()
 * 2.writeValueAsString()
 * 3.writeValueAsBytes()
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JavaToJsonDemo {

    @Test
    public void JavaToFileJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

        objectMapper.writeValue(new FileOutputStream("data/output-2.json"), car);
    }

    @Test
    public void JavaToJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("宝马");
        car.setDoors(4);

        String json = objectMapper.writeValueAsString(car);
        System.out.println(json);
    }

    @Test
    public void customerSerializer() throws JsonProcessingException {
        CarSerializer carSerializer = new CarSerializer(Car.class);
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));
        module.addSerializer(Car.class, carSerializer);

        objectMapper.registerModule(module);

        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);

        String carJson = objectMapper.writeValueAsString(car);
        System.out.println(carJson);
    }

}
