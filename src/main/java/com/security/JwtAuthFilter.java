package com.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.model.Userlogin;
import com.repository.LoginRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor 
public class JwtAuthFilter extends OncePerRequestFilter{

	
	private final LoginRepository loginRepository;
	private final AuthUtil authUtil;
	private final HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			log.info("Incoming request: {}"+request.getRequestURL());
			 
			final String requestTokenHeader = request.getHeader("Authorization");
			if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
			{
				filterChain.doFilter(request, response);
				return;
			}
			//"Bearer dsgfsh.svdghsds.sdvnsdvs"---->spilt-->0:"Bearer",1:"dsgfsh.svdghsds.sdvnsdvs"<--token
			System.out.println("Extract token");
			String token = requestTokenHeader.split("Bearer ")[1];//get the token
			
			//Verify signature with secreteKey and-->Valid-->give Authenticated username
			// Invalid signature--> Exception--> No return value
			String username = authUtil.getUsernameFromToken(token);
			System.out.println("requested user from token::"+username);
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()== null) 
			{
			 System.out.println("findByUsername::"+username);
			 Userlogin user = loginRepository.findByUsername(username).orElseThrow();
			 
			 //This creates an authenticated object containing:Principal → user,Authorities → user's roles/permissions
			 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					 new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
			 
			 //Spring Security can now say:current username, Authenticated = true,Put Authentication in SecurityContext
			 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			filterChain.doFilter(request, response);//continue with filter
		
		}catch(Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
		
	}

}
