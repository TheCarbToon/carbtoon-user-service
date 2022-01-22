package kr.springboot.dcinside.cartoon.userservice.messaging;

import kr.springboot.dcinside.cartoon.userservice.config.KafkaConfig;
import lombok.Getter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Deprecated
@Component
@Getter
public class UserEventProducer {

    /*private KafkaTemplate<String, Message> kafkaTemplate;
    private final String KAFKA_TOPIC = "carbtoon.user.update";

    public UserEventProducer() {
        this.kafkaTemplate = new KafkaConfig().kafkaTemplate();
    }

    public void send(Message message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }*/

}
