package kr.springboot.dcinside.cartoon.userservice.service.impl;

import kr.springboot.dcinside.cartoon.userservice.domain.Profile;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.exception.ResourceNotFoundException;
import kr.springboot.dcinside.cartoon.userservice.messaging.UserEventSender;
import kr.springboot.dcinside.cartoon.userservice.payload.UserSummary;
import kr.springboot.dcinside.cartoon.userservice.repo.UserRepository;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEventSender userEventSender;

    @Override
    public List<User> findAll() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("retrieving user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameIn(List<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    @Override
    public User updateProfilePicture(String uri, String id) {
        log.info("update profile picture {} for user {}", uri, id);

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

                    userEventSender.sendUserUpdated(savedUser, oldProfile.getProfilePictureUrl());

                    return savedUser;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("user id %s not found", id)));
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

}
