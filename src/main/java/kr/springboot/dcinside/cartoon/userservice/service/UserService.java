package kr.springboot.dcinside.cartoon.userservice.service;

import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo.AuthUserPojo;
import kr.springboot.dcinside.cartoon.userservice.dto.response.ApiResponse;
import kr.springboot.dcinside.cartoon.userservice.payload.UserSummary;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    List<User> findByUsernameIn(List<String> usernames);

    User updateProfileDisplayName(String uri, Long id);

    User updateProfilePicture(String uri, Long id);

    UserSummary convertTo(User user);

//    User saveUser(AuthUserPojo authUserPojo);

    User createAuthUser(String jsonAuthUser);

}
