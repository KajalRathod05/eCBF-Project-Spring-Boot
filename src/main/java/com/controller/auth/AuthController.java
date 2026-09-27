package com.controller.auth;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.DTOs.auth.UserLoginRequest;
import com.DTOs.auth.UserLoginResponse;
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
        return ResponseEntity.ok(authService.login(userLoginRequest));
	   
	}
	
	@PostMapping("/userRegister")
	public ResponseEntity<Map<String, Object>> userRegistration(@RequestBody Userlogin login) {
		
	    Map<String, Object> response = new HashMap<>();
        loginService.userRegistration(login);
        
        response.put("status", "success");
        response.put("message", "Registration Successful!");
        return ResponseEntity.ok(response);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Userlogin login) {

		 Map<String, Object> response = new HashMap<>();
    	 loginService.resetPassword(login);
    	 response.put("status", "success");
         response.put("message", "Password reset successfully");
         return ResponseEntity.ok(response);
	}

}
