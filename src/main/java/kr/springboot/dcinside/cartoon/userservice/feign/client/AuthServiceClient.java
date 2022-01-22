package kr.springboot.dcinside.cartoon.userservice.feign.client;

import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserDisplayNameUpdateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserPasswordUpdateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserProfilePictureUpdateFeignRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @PutMapping(value = "/auth/users/displayname", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean updateUserDisplayName(@RequestBody UserDisplayNameUpdateFeignRequest userDisplayNameUpdateFeignRequest);

    @PutMapping(value = "/auth/users/password", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean updateUserPassword(@RequestBody UserPasswordUpdateFeignRequest userPasswordUpdateFeignRequest);

    @PutMapping(value = "/auth/users/profile-picture", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean updateUserProfilePicture(@RequestBody UserProfilePictureUpdateFeignRequest userProfilePictureUpdateFeignRequest);

}
