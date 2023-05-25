package cn.tanglaoaer;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class JsonGeneratorDemo {
    @Test
    public void createInstance() throws IOException {
        JsonFactory factory = new JsonFactory();
        // 生成Java对象写入到文件中
        JsonGenerator generator = factory.createGenerator(new File("data/output.json"), JsonEncoding.UTF8);
        generator.writeStartObject();
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeEndObject();
        generator.close();
        System.out.println(generator);
    }
}
