package kr.springboot.dcinside.cartoon.userservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserUpdateRequest {

    private String displayName;

    private String password;

    @Builder
    public UserUpdateRequest(String displayName, String password) {
        this.displayName = displayName;
        this.password = password;
    }

}
