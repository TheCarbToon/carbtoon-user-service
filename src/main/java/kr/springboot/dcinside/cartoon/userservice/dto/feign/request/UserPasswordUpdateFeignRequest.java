package kr.springboot.dcinside.cartoon.userservice.dto.feign.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPasswordUpdateFeignRequest {

    private String password;

    private String id;

    private String lbServiceName;

    @Builder
    public UserPasswordUpdateFeignRequest(String password, String id, String lbServiceName) {
        this.password = password;
        this.id = id;
        this.lbServiceName = lbServiceName;
    }

}
