package kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthUserPojo{

	@JsonProperty("createdAt")
	private Object createdAt;

	@JsonProperty("password")
	private String password;

	@JsonProperty("roles")
	private List<RolesItem> roles;

	@JsonProperty("active")
	private boolean active;

	@JsonProperty("id")
	private String id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("userProfile")
	private UserProfile userProfile;

	@JsonProperty("username")
	private String username;

	@JsonProperty("updatedAt")
	private Object updatedAt;

	public Object getCreatedAt(){
		return createdAt;
	}

	public String getPassword(){
		return password;
	}

	public List<RolesItem> getRoles(){
		return roles;
	}

	public boolean isActive(){
		return active;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public UserProfile getUserProfile(){
		return userProfile;
	}

	public String getUsername(){
		return username;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}
}