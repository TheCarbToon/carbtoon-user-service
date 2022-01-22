package kr.springboot.dcinside.cartoon.userservice.dto.feign.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfilePictureUpdateFeignRequest {

    private String profilePictureUri;

    private String id;

    private String lbServiceName;

    @Builder
    public UserProfilePictureUpdateFeignRequest(String profilePictureUri, String id, String lbServiceName) {
        this.profilePictureUri = profilePictureUri;
        this.id = id;
        this.lbServiceName = lbServiceName;
    }

}
