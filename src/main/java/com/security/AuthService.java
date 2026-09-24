package com.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.DTOs.auth.UserLoginRequest;
import com.DTOs.auth.UserLoginResponse;
import com.model.Userlogin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final AuthUtil authUtil;
	
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		
		System.out.println("authenticationManager for user: "+ userLoginRequest.getUsername()+" Pass: "+userLoginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
			);  //Spring Security, please authenticate this username and password.by calling CustomeUserDetailsService
		//verify username and pass of user which is loaded by CustomuserDetailsService
		
		System.out.println("Given Authenticated User---------->");
		Userlogin user = (Userlogin) authentication.getPrincipal();//Give me the authenticated user.
		System.out.println("Given Authenticated User---------->"+user.getUsername());
		
		String token = authUtil.generateAccessToken(user);//Now create a JWT for this authenticated user.
		System.out.println("token:::"+token);
		return new UserLoginResponse(token, user.getUserid());
	}
}
