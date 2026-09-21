package com.DTOs;
import lombok.Data;

@Data
public class UserLoginResponse {
	
	private String jwt;
	private int userid;
	
	public UserLoginResponse(String jwt, int userid) {
		super();
		this.jwt = jwt;
		this.userid = userid;
	}
	
}
