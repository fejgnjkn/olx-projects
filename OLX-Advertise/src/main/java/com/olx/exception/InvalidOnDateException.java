package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidOnDateException extends RuntimeException{

	private String message;

	public InvalidOnDateException() {
		super();
		this.message = "";
	}
	
	public InvalidOnDateException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Inavlid On Date "+ this.message;
	}
	
	
	
	
}