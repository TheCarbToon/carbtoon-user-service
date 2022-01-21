package kr.springboot.dcinside.cartoon.userservice.dto.feign.request.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RolesItem{

	@JsonProperty("name")
	private String name;

	public String getName(){
		return name;
	}
}