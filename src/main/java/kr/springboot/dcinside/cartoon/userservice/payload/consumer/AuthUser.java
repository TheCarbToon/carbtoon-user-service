package kr.springboot.dcinside.cartoon.userservice.payload.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.springboot.dcinside.cartoon.userservice.domain.Profile;
import kr.springboot.dcinside.cartoon.userservice.domain.Role;
import kr.springboot.dcinside.cartoon.userservice.domain.User;

@Deprecated
public class AuthUser{

//	@JsonProperty("headers")
//	private Headers headers;
//
//	@JsonProperty("payload")
//	private Payload payload;
//
//	public Headers getHeaders(){
//		return headers;
//	}
//
//	public Payload getPayload(){
//		return payload;
//	}

//	public User toEntity() {
//		return User.builder()
//				.authId(this.getPayload().getId())
//				.username(this.getPayload().getUsername())
//				.password(this.getPayload().getPassword())
//				.email(this.getPayload().getEmail())
//				.active(true)
//				.roles(Role.USER)
//				.userProfile(Profile.builder()
//						.displayName(this.getPayload().getDisplayName())
//						.profilePictureUrl(this.getPayload().getProfilePictureUrl())
//						.build())
//				.build();
//	}

}