package com.needle.demoApi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthorIdException extends RuntimeException{
	
	public AuthorIdException(String message) {
		super(message);
	}

}
