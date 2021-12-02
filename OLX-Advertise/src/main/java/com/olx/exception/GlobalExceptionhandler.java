package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionhandler  extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {InavlidStatusIdException.class})
	public ResponseEntity<Object> handleStatusIdException(RuntimeException exception, 
			WebRequest webRequest){
	
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}

	
	@ExceptionHandler(value= {InvalidAdvertiseIdException.class})
	public ResponseEntity<Object> handleAdvertiseIdException(RuntimeException exception, 
			WebRequest webRequest){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value= {InvalidAuthTokenException.class})
	public ResponseEntity<Object> handleInvalidAuthTokenException(RuntimeException exception, WebRequest webRequest){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}

}

