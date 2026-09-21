package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.repository.LoginRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	//This means Spring Security asks--->I received username = Kajal. Where can I find Kajal?
	//Because public class Userlogin implements UserDetails
	//Here User loaded inside Spring Security using username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("call loadUserByUsername and checkin DB for user: "+ username );
		return (UserDetails) loginRepository.findByUsername(username).orElseThrow();
	}

}
