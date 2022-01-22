package kr.springboot.dcinside.cartoon.userservice.messaging;

import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.payload.UserEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Deprecated
@RequiredArgsConstructor
@Service
@Slf4j
public class UserEventSender {

    /*private final UserEventProducer userEventProducer;

    public void sendUserUpdated(User user, String updateContent) {
        log.info("sending user updated ({}) event for user {}", updateContent, user.getUsername());
        sendUserChangedEvent(convertTo(user, UserEventType.UPDATE));
    }

//    public void sendUserUpdated(User user, String oldPicUrl) {
//        log.info("sending user updated (profile pic changed) event for user {}",
//                user.getUsername());
//
//        UserEventPayload payload = convertTo(user, UserEventType.UPDATED);
//        payload.setOldProfilePicUrl(oldPicUrl);
//
//        sendUserChangedEvent(payload);
//    }

    private void sendUserChangedEvent(UserEventPayload payload) {

        Message<UserEventPayload> message =
                MessageBuilder
                        .withPayload(payload)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId())
                        .build();
        userEventProducer.send(message);

        log.info("user event {} sent to topic {} for user {}",
                message.getPayload().getEventType().name(),
                userEventProducer.getKAFKA_TOPIC(),
                message.getPayload().getUsername());
    }

    private UserEventPayload convertTo(User user, UserEventType eventType) {

        return UserEventPayload
                .builder()
                .eventType(eventType)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .displayName(user.getUserProfile().getDisplayName())
                .profilePictureUrl(user.getUserProfile().getProfilePictureUrl()).build();

    }*/

}
