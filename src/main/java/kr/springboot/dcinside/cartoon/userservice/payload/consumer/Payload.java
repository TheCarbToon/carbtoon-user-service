package kr.springboot.dcinside.cartoon.userservice.payload.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload{

	@JsonProperty("profilePictureUrl")
	private String profilePictureUrl;

	@JsonProperty("displayName")
	private String displayName;

	@JsonProperty("id")
	private String id;

	@JsonProperty("oldProfilePicUrl")
	private String oldProfilePicUrl;

	@JsonProperty("eventType")
	private String eventType;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public String getProfilePictureUrl(){
		return profilePictureUrl;
	}

	public String getDisplayName(){
		return displayName;
	}

	public String getId(){
		return id;
	}

	public Object getOldProfilePicUrl(){
		return oldProfilePicUrl;
	}

	public String getEventType(){
		return eventType;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}

}