package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequset;
import com.olx.dto.User;
import com.olx.security.JwtUtil;
import com.olx.services.LoginServices;
import com.olx.services.UserDetailsServiceImp;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/user")
public class LoginController {

	@Autowired
	LoginServices loginServices;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Register a user")
	public User registerUser(@RequestBody User user) {
		return loginServices.registerUser(user);
	}

	@GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Get User")
	public User getUser(@RequestHeader("Authorization") String token) {
		return loginServices.getUser(token);
	}
	
	@GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Get logged in user name")
	public String getLoggedInUserName(@RequestHeader("Authorization") String token) {
		return loginServices.getLoggedInUserName(token);
	}

	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Authenticate user")
	public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequset authenticationRequset) {
		return loginServices.authenticateUser(authenticationRequset);
	}

	@GetMapping(value = "/validate/token")
	@ApiOperation("Validate user token")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken) {
		return loginServices.validateToken(authToken);
	}

	@DeleteMapping(value = "/logout")
	@ApiOperation("Logout")
	public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String authToken) {

		return loginServices.logout(authToken);
	}

}
