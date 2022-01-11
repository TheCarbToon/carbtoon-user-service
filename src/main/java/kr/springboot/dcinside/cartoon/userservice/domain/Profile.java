package kr.springboot.dcinside.cartoon.userservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String displayName;

    private String profilePictureUrl;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Profile(Long id, User user, String displayName, String profilePictureUrl) {
        this.id = id;
        this.user = user;
        this.displayName = displayName;
        this.profilePictureUrl = profilePictureUrl;
    }

}

