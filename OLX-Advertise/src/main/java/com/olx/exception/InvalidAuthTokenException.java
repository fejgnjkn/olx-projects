package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException  extends RuntimeException{

	private String message;

	public InvalidAuthTokenException(String message) {
		super();
		this.message = message;
	}
	
	
	public InvalidAuthTokenException() {
		super();
		this.message = "";
	}


	@Override
	public String toString() {
		return "Inavlid Auth Token "+ this.message;
	}
	
	
	
}




