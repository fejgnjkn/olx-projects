package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidDateConditionException extends RuntimeException{

	private String message;

	
	public InvalidDateConditionException() {
		super();
		this.message = "";
	}
	
	public InvalidDateConditionException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Inavlid date condition"+this.message;
	}
	
	
}