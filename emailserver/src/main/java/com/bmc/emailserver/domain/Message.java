package com.bmc.emailserver.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

	private final Integer number;
	private final String subject;
	private final String from;
	private final String body;
	
	@Builder
	public Message(Integer number, String subject, String from, String body) {
		this.number = number;
		this.subject = subject;
		this.from = from;
		this.body = body;
	}
	
	
}
