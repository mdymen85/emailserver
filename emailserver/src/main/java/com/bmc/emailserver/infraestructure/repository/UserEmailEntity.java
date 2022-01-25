package com.bmc.emailserver.infraestructure.repository;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEmailEntity {

	private final String email;
	private final String password;
	private final String username;

	@Builder
	public UserEmailEntity(String email, String password, String username) {
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
}
