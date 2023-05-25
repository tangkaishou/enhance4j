package cn.tanglaoaer.annotation.read;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @date 2023/4/16
 */
public class OptimizedBooleanDeserializer
        extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext) throws
            IOException, JsonProcessingException {

        String text = jsonParser.getText();
        if("0".equals(text)) return false;
        return true;
    }
}
