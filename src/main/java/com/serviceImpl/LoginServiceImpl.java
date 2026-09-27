package com.serviceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exception.GlobalException;
import com.model.Userlogin;
import com.repository.LoginRepository;
import com.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepository loginRepository; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public void userRegistration(Userlogin login) {
		if (loginRepository.existsByUsername(login.getUsername())) {
	        throw new GlobalException("Username already exists");
	    }
	    if (loginRepository.existsByEmail(login.getEmail())) {
	        throw new GlobalException("Email already registered");
	    }
	    String encodedPassword =
	            passwordEncoder.encode(login.getPassword());

	    login.setPassword(encodedPassword);
	    loginRepository.save(login);	
	}

	@Override
	public void resetPassword(Userlogin login) {

	    Optional<Userlogin> optionalUser =loginRepository.findByUsernameAndEmail(login.getUsername(),login.getEmail());

	    if (optionalUser.isEmpty()) {
	        throw new GlobalException("Username and email do not match!");
	    }
	    Userlogin user = optionalUser.get();
	    if (passwordEncoder.matches(
	            login.getPassword(),
	            user.getPassword())) {
	        throw new GlobalException("New Password and old Password should be Different!");
	    }
	    String encodedNewPassword =	passwordEncoder.encode(login.getPassword());

	    user.setPassword(encodedNewPassword);
	    loginRepository.save(user);
	}
	
	

}
