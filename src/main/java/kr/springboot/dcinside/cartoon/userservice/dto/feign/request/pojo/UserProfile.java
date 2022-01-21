package kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile{

	@JsonProperty("profilePictureUrl")
	private Object profilePictureUrl;

	@JsonProperty("displayName")
	private String displayName;

	public Object getProfilePictureUrl(){
		return profilePictureUrl;
	}

	public String getDisplayName(){
		return displayName;
	}
}