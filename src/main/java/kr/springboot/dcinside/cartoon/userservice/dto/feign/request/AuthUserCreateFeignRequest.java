package kr.springboot.dcinside.cartoon.userservice.dto.feign.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthUserCreateFeignRequest {

    String jsonAuthUser;

    String lbServiceName;

    @Builder
    public AuthUserCreateFeignRequest(String jsonAuthUser, String lbServiceName) {
        this.jsonAuthUser = jsonAuthUser;
        this.lbServiceName = lbServiceName;
    }

}
