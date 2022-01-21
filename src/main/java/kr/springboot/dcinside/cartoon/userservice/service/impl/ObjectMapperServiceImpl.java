package kr.springboot.dcinside.cartoon.userservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.springboot.dcinside.cartoon.userservice.payload.consumer.AuthUser;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperServiceImpl {

//    @SneakyThrows
//    @Override
//    public <T> T readValue(Message message, Class<T> valueType) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(message.getPayload().toString(), valueType);
//    }

}
