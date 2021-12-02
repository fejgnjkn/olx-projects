package com.olx.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoginDelegateImp implements LoginDelagate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public boolean validateToken(String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(token.substring(7,token.length()));
		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		return restTemplate
				.exchange("http://api-gateway/olx/user/validate/token", HttpMethod.GET, entity, Boolean.class)
				.getBody().booleanValue();

	}

	@Override
	public String getLoggedInUserName(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(token.substring(7,token.length()));
		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		return restTemplate
				.exchange("http://api-gateway/olx/user/username", HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	public List<Map> fallbackValidateToken(String token, Exception ex) {
		System.out.println("Fallback validateToken failed " + ex);
		return null;
	}

	public List<Map> fallbackLoggedInUserName(String token, Exception ex) {
		System.out.println("Fallback get LoggedInUserName failed " + ex);
		return null;
	}

}
