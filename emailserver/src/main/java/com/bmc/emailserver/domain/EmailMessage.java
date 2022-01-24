package com.bmc.emailserver.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailMessage {

	private final String email;
	private final String username;
	private List<Message> messages;
	
	@Builder
	public EmailMessage(String email, String username, List<Message> messages) {
		this.email = email;
		this.username = username;
		this.messages = messages;
	}
	
}
