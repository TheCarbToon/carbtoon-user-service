package kr.springboot.dcinside.cartoon.userservice.service;

import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.payload.UserSummary;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    List<User> findByUsernameIn(List<String> usernames);

    User updateProfilePicture(String uri, String id);

    UserSummary convertTo(User user);

}
