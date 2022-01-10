package kr.springboot.dcinside.cartoon.userservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Profile {

    private String displayName;
    private String profilePictureUrl;

    @Builder
    public Profile(String displayName, String profilePictureUrl) {
        this.displayName = displayName;
        this.profilePictureUrl = profilePictureUrl;
    }

}

