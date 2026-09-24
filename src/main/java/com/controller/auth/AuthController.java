package com.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTOs.auth.UserLoginRequest;
import com.DTOs.auth.UserLoginResponse;
import com.exception.LoginException;
import com.model.Userlogin;
import com.security.AuthService;
import com.service.LoginService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	
	private final AuthService authService;
	private final LoginService loginService;
		
	@PostMapping("/userlogin")
	public ResponseEntity<UserLoginResponse>  userLogin(@RequestBody UserLoginRequest userLoginRequest) {	
		
		System.out.println("username: " + userLoginRequest.getUsername());
		//System.out.println("pass: "+ login.getPassword());		
	
        return ResponseEntity.ok(authService.login(userLoginRequest));
	   
	}
	
	@PostMapping("/userRegister")
	public ResponseEntity<String> userRegistration(@RequestBody Userlogin login) {
	    try {
	        loginService.userRegistration(login);
	        return ResponseEntity.ok("Registration Successful!");

	    } catch (LoginException e) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(e.getMessage());
	    }
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody Userlogin login) {

	    try {
	    	loginService.resetPassword(login);
	        return ResponseEntity.ok("Password reset successfully");
	    } catch (LoginException e) {

	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(e.getMessage());
	    }
	}

}
