package Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Input;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class userDeserializer implements Deserializer<Input> {
    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public Input deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Input user = null;
        try {
            user = mapper.readValue(arg1, Input.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

