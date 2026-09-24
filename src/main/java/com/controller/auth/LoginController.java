package com.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;



//@CrossOrigin("*")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	LoginService loginservice;
	
	
	//   @GetMapping("/") public String home(HttpServletRequest request ) {
	  
	//    return "Hello Kajal.."+request.getSession().getId(); 
	//   } 
	 
	 
	
}
