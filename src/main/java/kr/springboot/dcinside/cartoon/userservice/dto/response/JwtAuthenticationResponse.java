package kr.springboot.dcinside.cartoon.userservice.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }
    
}
