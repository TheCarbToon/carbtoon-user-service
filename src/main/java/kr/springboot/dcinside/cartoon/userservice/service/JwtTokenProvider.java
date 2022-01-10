package kr.springboot.dcinside.cartoon.userservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {

    Claims getClaimsFromJWT(String token);

    boolean validateToken(String authToken);

}
