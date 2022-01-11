package kr.springboot.dcinside.cartoon.userservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.payload.consumer.AuthUser;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
@Slf4j
public class UserEventConsumer {

    private final UserService userService;

    @KafkaListener(topics = "carbtoon.user.create", groupId = "")
    public void consume(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthUser authUser = objectMapper.readValue(message.getPayload().toString(), AuthUser.class);
        User saveUser = userService.saveUser(authUser.toEntity());
        log.info("auth service create user save success here, auth id is {}, here id {}", saveUser.getAuthId(), saveUser.getId());
    }

}
