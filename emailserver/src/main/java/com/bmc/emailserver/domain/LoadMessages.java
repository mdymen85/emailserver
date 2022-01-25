package com.bmc.emailserver.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoadMessages implements IEmailSession {

	private final String email;
	private final String password;
	
	@Builder
	public LoadMessages(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
