package cn.tanglaoaer;

import cn.tanglaoaer.vo.Car;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonParserDemo {
    private JsonToken jsonToken;

    @Test
    public void createParserTest() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(carJson);
    }

    @Test
    public void user() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser  parser  = factory.createParser(carJson);

        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();
            System.out.println(parser.getCurrentName());
            parser.nextToken();
            System.out.println("jsonToken = " + jsonToken);
        }
    }

    @Test
    public void parse() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser  parser  = factory.createParser(carJson);

        Car car = new Car();
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if("brand".equals(fieldName)){
                    car.setBrand(parser.getValueAsString());
                } else if ("doors".equals(fieldName)){
                    car.setDoors(parser.getValueAsInt());
                }
            }
        }

        System.out.println("car.brand = " + car.getBrand());
        System.out.println("car.doors = " + car.getDoors());
    }
}
