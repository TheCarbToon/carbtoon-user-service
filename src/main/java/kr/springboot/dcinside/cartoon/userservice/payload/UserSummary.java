package kr.springboot.dcinside.cartoon.userservice.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSummary {

    private Long id;
    private String username;
    private String name;
    private String profilePicture;

    @Builder
    public UserSummary(Long id, String username, String name, String profilePicture) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.profilePicture = profilePicture;
    }

}
