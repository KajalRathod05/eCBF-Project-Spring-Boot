package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleUsernameNotFoundExceprion(UsernameNotFoundException exp){
		ApiError apiError = new ApiError("Username Not Found Exception: "+exp.getMessage(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException exp){
		ApiError apiError = new ApiError("Authentication Exception: "+exp.getMessage(),HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiError> handleJwtException(JwtException exp){
		ApiError apiError = new ApiError("Invalid JWT Token: "+exp.getMessage(),HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException exp){
		ApiError apiError = new ApiError("Access Denied: Insufficeint Permission",HttpStatus.FORBIDDEN);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException exp){
		ApiError apiError = new ApiError("Invalid Credentials.",HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(Exception exp){
		ApiError apiError = new ApiError("An unexpected error occurred: "+exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(apiError,apiError.getStatusCode());
	}
}
