package kr.springboot.dcinside.cartoon.userservice.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@Deprecated
public interface UserEventStream {

    String OUTPUT = "cartoonUserChanged";

    @Output(OUTPUT)
    MessageChannel cartoonUserChanged();

}
