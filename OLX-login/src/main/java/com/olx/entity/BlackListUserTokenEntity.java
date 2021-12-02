package com.olx.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TOKENS")
public class BlackListUserTokenEntity {
	
	@Id
	private String token;
	public BlackListUserTokenEntity(String token) {
		super();
		this.token = token;
	}
	public BlackListUserTokenEntity() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Black listUserToken entity [ token=" + token + "]";
	}
	
	

}
