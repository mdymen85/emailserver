package com.bmc.emailserver.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
public class Email implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private User user;
	
	@Builder
	public Email(String email, String password, User user) {
		this.email = email;
		this.password = password;
		this.user = user;
	}
	
	protected void setUser(User user) {
		this.user = user;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
