package kr.springboot.dcinside.cartoon.userservice.controller;

import kr.springboot.dcinside.cartoon.userservice.domain.CartoonUserDetails;
import kr.springboot.dcinside.cartoon.userservice.domain.User;
import kr.springboot.dcinside.cartoon.userservice.dto.feign.request.AuthUserCreateFeignRequest;
import kr.springboot.dcinside.cartoon.userservice.dto.response.ApiResponse;
import kr.springboot.dcinside.cartoon.userservice.dto.response.Result;
import kr.springboot.dcinside.cartoon.userservice.exception.ResourceNotFoundException;
import kr.springboot.dcinside.cartoon.userservice.payload.*;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 프로필 이미지 업데이트
     *
     * @param profilePicture
     * @param userDetails
     * @return
     */
    @PutMapping("/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProfilePicture(
            @RequestBody String profilePicture,
            @AuthenticationPrincipal CartoonUserDetails userDetails) {

        userService.updateProfilePicture(profilePicture, userDetails.getId());

        return ResponseEntity
                .accepted()
                .body(new ApiResponse(true, "프로필 사진 업데이트 성공!"));
    }

    /**
     * 프로필 닉네임 업데이트
     *
     * @param displayName
     * @param userDetails
     * @return
     */
    @PutMapping("/displayname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserDisplayName(
            @RequestBody String displayName,
            @AuthenticationPrincipal CartoonUserDetails userDetails) {

        userService.updateUserDisplayName(displayName, userDetails.getId());

        return ResponseEntity
                .accepted()
                .body(new ApiResponse(true, "프로필 닉네임 업데이트 성공!"));

    }

    /**
     * 유저 패스워드 업데이트
     *
     * @param password
     * @param userDetails
     * @return
     */
    @PutMapping("/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody String password,
            @AuthenticationPrincipal CartoonUserDetails userDetails) {

        userService.updateUserPassword(password, userDetails.getId());

        return ResponseEntity
                .accepted()
                .body(new ApiResponse(true, "비밀번호 업데이트 성공!"));

    }

//    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findUser(@PathVariable("username") String username) {
//        log.info("retrieving user {}", username);
//
//        return  userService
//                .findByUsername(username)
//                .map(user -> ResponseEntity.ok(user))
//                .orElseThrow(() -> new ResourceNotFoundException(username));
//    }

    /**
     * 본인 데이터 확인
     *
     * @param userDetails
     * @return
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(
            @AuthenticationPrincipal CartoonUserDetails userDetails) {
        return ResponseEntity.ok(UserSummary
                .builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .name(userDetails.getUserProfile().getDisplayName())
                .profilePicture(userDetails.getUserProfile().getProfilePictureUrl())
                .build());
    }

    /**
     * 유저 1개만 가져오기
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/summary/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserSummary(
            @PathVariable("username") String username) {
        log.info("유저 찾기 -> {}", username);

        return userService
                .findByUsername(username)
                .map(user -> ResponseEntity.ok(userService.convertTo(user)))
                .orElseThrow(() -> new ResourceNotFoundException(username));
    }

    /**
     * 유저 여러개 가져오기
     *
     * @param usernames
     * @return
     */
    @PostMapping(value = "/summary/in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserSummaries(@RequestBody List<String> usernames) {
        log.info("유저 여러개 찾기 -> {} usernames", usernames.size());

        List<UserSummary> summaries =
                userService
                        .findByUsernameIn(usernames)
                        .stream()
                        .map(user -> userService.convertTo(user))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(summaries);

    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTest() {
        log.info("feign test");
        return ResponseEntity.ok(new Result("Test Page HAHAHHAHAHAH"));
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(
            @RequestBody AuthUserCreateFeignRequest authUserCreateFeignRequest) {
        log.info("feign create user");
        if (authUserCreateFeignRequest.getLbServiceName().equals("AUTH-SERVICE")) {
            User user = userService.createAuthUser(authUserCreateFeignRequest.getJsonAuthUser());
            if (user.getId() == null) {
                return ResponseEntity.badRequest().body(new Result("It's not OK!!"));
            }
            return ResponseEntity.ok(new Result("It's OK!"));
        }
        return ResponseEntity.badRequest().body(new Result("It's not OK!!"));
    }

}
