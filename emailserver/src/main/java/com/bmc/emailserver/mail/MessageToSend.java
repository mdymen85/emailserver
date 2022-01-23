package com.bmc.emailserver.mail;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
public class MessageToSend {
	
	private String from;
	@Singular("recipient")
	private Set<String> recipients;
	private String subject;
	private String text;
	private String password;
	
	@Builder
	public MessageToSend(String from, Set<String> recipients, String subject, String text) {
		
		if (from == null || from.equals("")) {
			throw new IllegalStateException();
		}
		
		if (recipients == null || recipients.size() < 1) {
			throw new IllegalStateException();
		}
		
		if (subject == null || subject.equals("")) {
			throw new IllegalStateException();
		}
		
		if (text == null || text.equals("")) {
			throw new IllegalStateException();
		}
		
		this.from = from;
		this.recipients = recipients;
		this.subject = subject;
		this.text = text;
	}

	
}
