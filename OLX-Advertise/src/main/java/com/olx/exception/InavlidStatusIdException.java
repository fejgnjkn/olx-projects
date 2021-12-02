package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InavlidStatusIdException extends RuntimeException{

	private String message;

	
	public InavlidStatusIdException() {
		super();
		this.message = "";
	}
	
	public InavlidStatusIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "InavlidStatusIdException [message=" + message + "]"+ this.message;
	}
	
	
	
	
}
