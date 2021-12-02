package com.olx.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.olx.dto.AuthenticationRequset;
import com.olx.dto.User;

public interface LoginServices {

	public User registerUser(User user);

	public User getUser(String token);

	public ResponseEntity<Boolean> validateToken( String authToken);

	public ResponseEntity<String> authenticateUser(AuthenticationRequset authenticationRequset);
	
	public ResponseEntity<Boolean> logout(String authToken);
	
	public String getLoggedInUserName(String authToken);
}
