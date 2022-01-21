package kr.springboot.dcinside.cartoon.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;

public abstract class ObjectMapperProvider {

    @SneakyThrows
    public <T> T readValue(Message message, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message.getPayload().toString(), valueType);
    }

}
