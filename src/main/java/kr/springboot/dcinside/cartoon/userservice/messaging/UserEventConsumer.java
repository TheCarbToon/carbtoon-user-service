package kr.springboot.dcinside.cartoon.userservice.messaging;

import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.payload.consumer.AuthUser;
import kr.springboot.dcinside.cartoon.userservice.service.ObjectMapperProvider;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Deprecated
@RequiredArgsConstructor
@Component
@Getter
@Slf4j
public class UserEventConsumer {

//    private final UserService userService;

//    @Deprecated
//    @KafkaListener(topics = "carbtoon.user.create", groupId = "")
//    public void consume(Message message) {
//        AuthUser authUser = mapperProvider.readValue(message, AuthUser.class);
//        if (authUser.getPayload().getEventType().equals("CREATED")) {
//            User saveUser = userService.saveUser(authUser.toEntity());
//            log.info("인증 서비스에서 생성된 유저 ({}), 유저 서비스에 생성 완료 ID -> ({})", saveUser.getAuthId(), saveUser.getId());
//        }
//    }

}
