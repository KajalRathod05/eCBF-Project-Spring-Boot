package com.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ApiError {

	private LocalDateTime timeStamp;
	private String message;
	private HttpStatus StatusCode;
	
	//public ApiError() {this.timeStamp= LocalDateTime.now();}

	public ApiError( String message, HttpStatus StatusCode) {
		super();
		this.timeStamp= LocalDateTime.now();
		this.message = message;
		this.StatusCode = StatusCode;
	}
	
	
}
