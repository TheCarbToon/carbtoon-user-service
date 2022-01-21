package kr.springboot.dcinside.cartoon.userservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@NamedEntityGraph
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authId;

    @NotBlank
    @Size(max = 25)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean active;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Profile userProfile;

    @Enumerated(EnumType.STRING)
    private Role roles;

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }

    public User(User user) {
        this.id = user.id;
        this.authId = user.authId;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.active = user.active;
        this.userProfile = user.userProfile;
        this.roles = user.roles;
    }

    @Builder
    public User(Long id, String authId, String username, String password, String email, LocalDateTime createdAt, LocalDateTime updatedAt, boolean active, Profile userProfile, Role roles) {
        this.id = id;
        this.authId = authId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
        this.userProfile = userProfile;
        this.roles = roles;
    }

}
