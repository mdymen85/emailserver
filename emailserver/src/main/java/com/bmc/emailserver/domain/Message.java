package com.bmc.emailserver.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Message {

	private final String subject;
	private final String from;
	private final String body;
	
	@Builder
	public Message(String subject, String from, String body) {
		this.subject = subject;
		this.from = from;
		this.body = body;
	}
	
	
}
