package kr.springboot.dcinside.cartoon.userservice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventStream {

    String OUTPUT = "cartoonUserChanged";

    @Output(OUTPUT)
    MessageChannel cartoonUserChanged();

}
