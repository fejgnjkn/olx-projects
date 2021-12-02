package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidStatusIdException extends RuntimeException {
	
	private String message;
	
	public InvalidStatusIdException() {
		super();
		this.message = "";
	} 

	public InvalidStatusIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Inavlid status id " + message;
	}

}
