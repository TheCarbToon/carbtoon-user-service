package kr.springboot.dcinside.cartoon.userservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.springboot.dcinside.cartoon.userservice.domain.Profile;
import kr.springboot.dcinside.cartoon.userservice.domain.Role;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo.AuthUserPojo;
import kr.springboot.dcinside.cartoon.userservice.exception.ResourceNotFoundException;
import kr.springboot.dcinside.cartoon.userservice.messaging.UserEventSender;
import kr.springboot.dcinside.cartoon.userservice.payload.UserSummary;
import kr.springboot.dcinside.cartoon.userservice.repo.UserRepository;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEventSender userEventSender;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        log.info("유저 전부 찾기");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        log.info("유저 찾기 -> {}", username);
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findByUsernameIn(List<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    @Override
    public User updateProfileDisplayName(String displayName, Long id) {
        log.info("업데이트 프로필 닉네임 -> {}, 유저 -> {}", displayName, id);
        return userProfileDisplayNameUpdate(displayName, id);
    }

    @Transactional
    User userProfileDisplayNameUpdate(String displayName, Long id) {
        String content = "profile displayname";
        return userRepository
                .findById(id)
                .map(user -> {
                    Profile oldProfile = user.getUserProfile();
                    Profile profile = Profile.builder()
                            .profilePictureUrl(oldProfile.getProfilePictureUrl())
                            .displayName(displayName)
                            .build();
                    user.setUserProfile(profile);
                    User savedUser = userRepository.save(user);
                    userEventSender.sendUserUpdated(savedUser, content);
                    return savedUser;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("%s의 사용자를 찾을수 없음.", id)));
    }

    @Override
    public User updateProfilePicture(String uri, Long id) {
        log.info("업데이트 프로필 사진 -> {}, 유저 -> {}", uri, id);
        return userProfilePictureUpdate(uri, id);
    }

    @Transactional
    User userProfilePictureUpdate(String uri, Long id) {
        String content = "프로필 사진";
        return userRepository
                .findById(id)
                .map(user -> {
                    Profile oldProfile = user.getUserProfile();
                    Profile profile = Profile.builder()
                            .profilePictureUrl(uri)
                            .displayName(oldProfile.getDisplayName())
                            .build();
                    user.setUserProfile(profile);
                    User savedUser = userRepository.save(user);
                    userEventSender.sendUserUpdated(savedUser, content);
                    return savedUser;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("%s의 사용자를 찾을수 없음.", id)));
    }

    @Transactional
    User saveUser(AuthUserPojo authUserPojo) {
        User user = User.builder()
                .active(true)
                .email(authUserPojo.getEmail())
                .password(authUserPojo.getPassword())
                .username(authUserPojo.getUsername())
                .authId(authUserPojo.getId())
                .roles(Role.USER)
                .build();
        Profile profile = Profile.builder().user(user).build();
        user.setUserProfile(profile);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public UserSummary convertTo(User user) {
        return UserSummary
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getUserProfile().getDisplayName())
                .profilePicture(user.getUserProfile().getProfilePictureUrl())
                .build();
    }

    @Override
    public User createAuthUser(String jsonAuthUser) {
        AuthUserPojo authUserPojo = null;
        try {
            authUserPojo = objectMapper.readValue(jsonAuthUser, AuthUserPojo.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        User user = saveUser(authUserPojo);
        return user;
    }
}
