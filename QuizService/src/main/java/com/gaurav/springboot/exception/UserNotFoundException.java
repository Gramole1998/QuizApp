package com.gaurav.springboot.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{
	
	HttpStatus errorcode;
	public UserNotFoundException(String message,HttpStatus badRequest){
		super(message);
		this.errorcode=badRequest;
	}
	
	public HttpStatus getErrorCode() {
		return errorcode;
	}

}
