package kr.springboot.dcinside.cartoon.userservice.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Result {

    String message;

    public Result(String message) {
        this.message = message;
    }

}
