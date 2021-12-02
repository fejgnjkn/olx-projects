package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidFromDateException extends RuntimeException{

	private String message;

	public InvalidFromDateException() {
		super();
		this.message = "";
	}
	
	
	public InvalidFromDateException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String toString() {
		return "Inavlid from date "+this.message;
	}
	
	
	
	
}