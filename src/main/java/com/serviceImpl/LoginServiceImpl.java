package com.serviceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.DTOs.auth.UserLoginRequest;
import com.exception.GlobalException;
import com.model.Userlogin;
import com.repository.LoginRepository;
import com.service.LoginService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepository loginRepository; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public void userRegistration(UserLoginRequest userLoginRequest) {
		if (loginRepository.existsByUsername(userLoginRequest.getUsername())) {
	        throw new GlobalException("Username already exists");
	    }
	    if (loginRepository.existsByEmail(userLoginRequest.getEmail())) {
	        throw new GlobalException("Email already registered");
	    }
	    String encodedPassword = passwordEncoder.encode(userLoginRequest.getPassword());
	    
	    Userlogin login = new Userlogin();
	    login.setUsername(userLoginRequest.getUsername());
	    login.setPassword(encodedPassword);
	    login.setEmail(userLoginRequest.getEmail());
	    loginRepository.save(login);	
	}

	@Override
	public void resetPassword(UserLoginRequest userLoginRequest) {

	    Optional<Userlogin> optionalUser =loginRepository.findByUsernameAndEmail(userLoginRequest.getUsername(),
	    		userLoginRequest.getEmail());

	    if (optionalUser.isEmpty()) {
	        throw new GlobalException("Username and email do not match!");
	    }
	    
	    Userlogin user = optionalUser.get();
	    
	    if (passwordEncoder.matches(userLoginRequest.getPassword(),user.getPassword())) {
	        throw new GlobalException("New Password and old Password should be Different!");
	    }
	    
	    String encodedNewPassword =	passwordEncoder.encode(userLoginRequest.getPassword());

	    user.setPassword(encodedNewPassword);
	    loginRepository.save(user);
	}
	
	
	

}
