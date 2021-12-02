package com.olx.dto;

import io.swagger.annotations.ApiModel;

@ApiModel("AUthenticate request model")
public class AuthenticationRequset {
	
	private String userName;
    private String password;
    
    public AuthenticationRequset() {
    	
    }
    
	public AuthenticationRequset(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthenticationRequset [userName=" + userName + ", password=" + password + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
