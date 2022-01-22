package kr.springboot.dcinside.cartoon.userservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.springboot.dcinside.cartoon.userservice.domain.Profile;
import kr.springboot.dcinside.cartoon.userservice.domain.Role;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserDisplayNameUpdateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserPasswordUpdateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.UserProfilePictureUpdateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo.AuthUserPojo;
import kr.springboot.dcinside.cartoon.userservice.exception.BadRequestException;
import kr.springboot.dcinside.cartoon.userservice.exception.ResourceNotFoundException;
import kr.springboot.dcinside.cartoon.userservice.feign.client.AuthServiceClient;
import kr.springboot.dcinside.cartoon.userservice.payload.UserSummary;
import kr.springboot.dcinside.cartoon.userservice.repo.ProfileRepository;
import kr.springboot.dcinside.cartoon.userservice.repo.UserRepository;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final String lbServiceName = "USER-SERVICE";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ObjectMapper objectMapper;
    private final AuthServiceClient authServiceClient;

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
    public User updateUserDisplayName(String displayName, Long id) {
        log.info("유저 닉네임 업데이트 -> {}", id);
        return userProfileDisplayNameUpdate(displayName, id);
    }

    @Transactional
    User userProfileDisplayNameUpdate(String displayName, Long id) {
        return userRepository
                .findById(id)
                .map(user -> {

                    Profile profile = user.getUserProfile();

                    if (!displayName.equals(profile.getDisplayName())) profile.setDisplayName(displayName);

                    profileRepository.save(profile);

                    boolean feignBool = authServiceClient.updateUserDisplayName(UserDisplayNameUpdateFeignRequest.builder()
                            .lbServiceName(lbServiceName)
                            .displayName(displayName)
                            .id(user.getAuthId())
                            .build());

                    if (!feignBool) throw new BadRequestException("/auth/users/displayname feign failure error");

                    return user;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("%s의 사용자를 찾을수 없음.", id)));
    }

    @Override
    public User updateUserPassword(String password, Long id) {
        log.info("유저 패스워드 업데이트 -> {}", id);
        return userPasswordUpdate(password, id);
    }

    @Transactional
    User userPasswordUpdate(String password, Long id) {
        return userRepository
                .findById(id)
                .map(user -> {
                    if (!passwordEncoder.matches(user.getPassword(), password)) {
                        user.setPassword(passwordEncoder.encode(password));
                    }
                    userRepository.save(user);

                    boolean feignBool = authServiceClient.updateUserPassword(UserPasswordUpdateFeignRequest.builder()
                            .lbServiceName(lbServiceName)
                            .password(user.getPassword())
                            .id(user.getAuthId())
                            .build());

                    if (!feignBool) throw new BadRequestException("/auth/users/password feign failure error");

                    return user;
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
        return userRepository
                .findById(id)
                .map(user -> {

                    Profile profile = user.getUserProfile();

                    if (!profile.getProfilePictureUrl().equals(uri)) {
                        profile.setProfilePictureUrl(uri);
                    }

                    profileRepository.save(profile);

                    boolean feignBool = authServiceClient.updateUserProfilePicture(UserProfilePictureUpdateFeignRequest.builder()
                            .lbServiceName(lbServiceName)
                            .profilePictureUri(uri)
                            .id(user.getAuthId())
                            .build());

                    if (!feignBool) throw new BadRequestException("/auth/users/profile-picture feign failure error");

                    return user;
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
        Profile profile = Profile.builder()
                .displayName(authUserPojo.getUserProfile().getDisplayName())
                .profilePictureUrl("")
                .user(user).build();
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
