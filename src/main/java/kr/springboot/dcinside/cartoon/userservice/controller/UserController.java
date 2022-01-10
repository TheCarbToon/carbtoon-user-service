package kr.springboot.dcinside.cartoon.userservice.controller;

import kr.springboot.dcinside.cartoon.userservice.domain.CartoonUserDetails;
import kr.springboot.dcinside.cartoon.userservice.dto.response.ApiResponse;
import kr.springboot.dcinside.cartoon.userservice.exception.ResourceNotFoundException;
import kr.springboot.dcinside.cartoon.userservice.payload.*;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PutMapping("/users/me/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity <?> updateProfilePicture(
            @RequestBody String profilePicture,
            @AuthenticationPrincipal CartoonUserDetails userDetails) {

        userService.updateProfilePicture(profilePicture, userDetails.getId());

        return ResponseEntity
                .ok()
                .body(new ApiResponse(true,"Profile picture updated successfully"));
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);

        return  userService
                .findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        log.info("retrieving all users");

        return ResponseEntity
                .ok(userService.findAll());
    }

    @GetMapping(value = "/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public UserSummary getCurrentUser(@AuthenticationPrincipal CartoonUserDetails userDetails) {
        return UserSummary
                .builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .name(userDetails.getUserProfile().getDisplayName())
                .profilePicture(userDetails.getUserProfile().getProfilePictureUrl())
                .build();
    }

    @GetMapping(value = "/users/summary/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserSummary(@PathVariable("username") String username) {
        log.info("retrieving user {}", username);

        return  userService
                .findByUsername(username)
                .map(user -> ResponseEntity.ok(userService.convertTo(user)))
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    @PostMapping(value = "/users/summary/in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserSummaries(@RequestBody List<String> usernames) {
        log.info("retrieving summaries for {} usernames", usernames.size());

        List<UserSummary> summaries =
                userService
                        .findByUsernameIn(usernames)
                        .stream()
                        .map(user -> userService.convertTo(user))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(summaries);

    }

}
