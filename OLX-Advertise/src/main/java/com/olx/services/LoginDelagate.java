package com.olx.services;

import org.springframework.stereotype.Service;

@Service
public interface LoginDelagate {
	
	public boolean validateToken(String token);
	public String getLoggedInUserName(String token);

}
